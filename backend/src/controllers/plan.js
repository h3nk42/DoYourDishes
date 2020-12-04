require('../utils/constant')

const Plan = require('../models/Plan')
const Task = require('../models/Task')
const User = require('../models/User')
const mongoose = require('mongoose');
const ObjectId = mongoose.Types.ObjectId;


const utils = require('../utils/index')
const {validationResult} = require("express-validator");

exports.findPlanToOwner = async (req, res) => {
    let msgSender = req.user.userName;
    let user = await User.findOne({userName: msgSender}, (err, data)=>{})
    console.log(user.plan)
    Plan.find( {_id: user.plan } ,(err, data) => {
        if (err) {
            return res.json({success: false, error: err});
        } else {
            return res.json({success: true, data: data});
        }
    });
}

exports.findAllPlans = (req, res) => {
    Plan.find((err, data) => {
        if (err) {
            console.log(utils.generateServerErrorCode(res, 403, err, PLAN_NOT_FOUND, 'findAllPlans'))
            return res.status(403).json(utils.generateServerErrorCode(res, 403, err, PLAN_NOT_FOUND, 'findAllPlans'));
        } else {
            return res.json({success: true, data: data});
        }
    });
}

exports.createPlan = async (req, res) => {
    let msgSender = req.user.userName;
    let plan = new Plan();
    const {name} = req.body;

    //check if user already has a plan
    let planExists = await Plan.exists({owner: msgSender})
    if (planExists)   return res.status(403).json(utils.generateServerErrorCode(res, 403, 'only 1 plan per user', '1_PLAN_USER', 'createPlan'));

    if (!name ) {
        console.log(utils.generateServerErrorCode(res, 403, 'no name given', NAME_IS_EMPTY, 'createPlan'))
        return res.status(403).json(utils.generateServerErrorCode(res, 403, 'no name given', NAME_IS_EMPTY, 'createPlan'));
    }
    plan.name = name;
    plan.users = [msgSender];
    plan.owner = msgSender;
    plan.tasks = [];
    console.log(plan)
    plan.save((err, plan) => {
        if (err) return res.json({success: false, error: err});
        User.updateOne({userName: msgSender}, {$set: {plan:plan._id}}, (err, updatedUser ) => { if(!err) {console.log(updatedUser)} } )
        return res.json({success: true});
    });
}

exports.deletePlan = (req, res) => {
    const {id} = req.body;
    console.log(req.body);
    if (!id ) {
        return res.json({error: "error no id"});
    }
    Plan.findById(id, (err, plan) => {
        if (!plan){return res.status(404).json(utils.generateServerErrorCode(res, 403, 'no plan exists', 'no plan exists', 'deletePlan'))}
        if(plan.owner !== req.user.userName) return res.status(402).json(
            utils.generateServerErrorCode(res, 403, err, 'Not plan owner', 'deletePlan')
        )

        Plan.deleteOne({_id: id}, (err) => {
            if (err) return res.send(err);
            Task.remove({plan: id}, (err, updatedPlan) => { if(err) {console.log(err)} })
            User.updateMany({plan: ObjectId(id)}, {$set: {plan: null}}, (err, data) => {
                console.log('users updated: ' + data.n)
            })
            return res.json({success: true});
        });
    })
}

exports.addUser = async (req, res) => {
    let msgSender = req.user.userName,
        userToAdd = req.body.userName;

    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        return res.status(400).json({ errors: errors.array() });
    }
    let plan = await Plan.findOne({owner: req.user.userName}, (err, plan) => {
        if(err) return res.status(400).json(
            utils.generateServerErrorCode(res, 403, err, 'Plan found error', 'addUser')
        )
    })
    if(!plan) return res.status(404).json(utils.generateServerErrorCode(res, 400, 'Plan not found in db', 'plan not found','addUser'))
    if(plan.users.includes(userToAdd)) return res.status(450).json(utils.generateServerErrorCode(res, 400, 'User already in this Plan', 'User in Plan','addUser'))
    let user = await User.findOne({userName: userToAdd}, (err)=>{
    })
    if (!user) return  res.status(404).json(utils.generateServerErrorCode(res, 400, 'User not found in db', 'User not found','addUser'))
    if (user.plan != null)  return  res.status(450).json(utils.generateServerErrorCode(res, 400, 'User already in another plan', 'User already in plan','addUser'))

    Plan.updateOne(
        { owner: msgSender },
        {
            $push: {
                users: [userToAdd]
            }
        },
        (err, updatedPlan) => { if(err) {console.log(err)} } )
    User.updateOne({userName: userToAdd} , {$set: {plan: plan._id}}, (err, data) => {
    })
    return res.json({success: true});
}
