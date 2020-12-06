require('../utils/constant')

const Plan = require('../models/Plan')
const Task = require('../models/Task')
const User = require('../models/User')
const mongoose = require('mongoose');
const ObjectId = mongoose.Types.ObjectId;

const {retErr} = require('../utils/index');
const {checkInputs} = require('../utils/index')



exports.findPlanToOwner = async (req, res) => {
    let msgSender = req.user.userName;
    let user = await User.findOne({userName: msgSender}, (err, data)=>{})
    Plan.findOne( {_id: user.plan } ,(err, data) => {
        if (err) {
            return  retErr(res, err, 418, 'DB_ERROR');
        } else {
            return res.status(200).json({data: data});
        }
    });
}

exports.findAllPlans = (req, res) => {
    Plan.find((err, data) => {
        if (err) {
            return  retErr(res, err, 418, 'PLAN_NOT_FOUND');
        } else {
            return res.status(200).json({data: data});
        }
    });
}

exports.createPlan = async (req, res) => {
    if(checkInputs(req,res)) return  retErr(res, {}, 418, 'INVALID_INPUT');
    let msgSender = req.user.userName;
    let plan = new Plan();
    const {name} = req.body;
    //check if user already has a plan
    let planExists = await Plan.exists({owner: msgSender})
    if (planExists)   return  retErr(res, {}, 418, 'ONLY_ONE_PLAN_PER_USER');

    plan.name = name;
    plan.users = [{userName: msgSender, points: 0}];
    plan.owner = msgSender;
    plan.tasks = [];
    plan.save((err, plan) => {
        if (err) return res.json({success: false, error: err});
        User.updateOne({userName: msgSender}, {$set: {plan:plan._id}}, (err, updatedUser ) => { if(!err) {} } )
        return res.json({success: true});
    });
}

exports.deletePlan = (req, res) => {
    if(checkInputs(req,res)) return  retErr(res, {}, 418, 'INVALID_INPUT');
    const {id} = req.body;
    Plan.findById(id, (err, plan) => {
        if (!plan){return res.status(404).json(utils.generateServerErrorCode(res, 403, 'no plan exists', 'no plan exists', 'deletePlan'))}
        if(plan.owner !== req.user.userName) return res.status(402).json(
            utils.generateServerErrorCode(res, 403, 'Youre not the plan owner..', 'Not plan owner', 'deletePlan')
        )
        Plan.deleteOne({_id: id}, (err) => {
            if (err) return res.send(err);
            Task.remove({plan: id}, (err, updatedPlan) => { if(err) {} })
            User.updateMany({plan: ObjectId(id)}, {$set: {plan: null}}, (err, data) => {
            })
            return res.json({success: true});
        });
    })
}

exports.addUser = async (req, res) => {
    if(checkInputs(req,res)) return  retErr(res, {}, 418, 'INVALID_INPUT');
    let msgSender = req.user.userName,
        userToAdd = req.body.userName;
    let plan = await Plan.findOne({owner: req.user.userName}, (err, plan) => {
        if(err)  return  retErr(res, err, 418, 'DB_ERROR');
    })
    if(!plan) return  retErr(res, {}, 418, 'PLAN_NOT_FOUND');
    if(plan.users.includes(userToAdd)) return  retErr(res, err, 418, 'USER_IN_PLAN_ALLREADY');
    let user = await User.findOne({userName: userToAdd}, (err)=>{
    })
    if (!user) return  retErr(res, {}, 418, 'USER_DOES_NOT_EXIST');
    if (user.plan != null)   return  retErr(res, {}, 418, 'USER_IN_ANOTHER_PLAN');

    Plan.updateOne(
        {owner: msgSender },
        {$push: {users: [{userName: userToAdd, points: 0}]}},
        (err, updatedPlan) => { if(err) return retErr(res, err, 418, 'DB_ERROR');})
    User.updateOne({userName: userToAdd} , {$set: {plan: plan._id}}, (err, data) => {
        if(err) return retErr(res, err, 418, 'DB_ERROR');
    })
    return res.status(200).json({data: true});
}


exports.kickUser = (req, res) => {

}
