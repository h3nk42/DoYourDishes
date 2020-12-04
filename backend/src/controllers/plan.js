require('../utils/constant')

const Plan = require('../models/Plan')
const Task = require('../models/Task')
const mongoose = require('mongoose');
const ObjectId = mongoose.Types.ObjectId;

const utils = require('../utils/index')

exports.findPlanToOwner = (req, res) => {
    Plan.find( {owner: req.user.userName} ,(err, data) => {
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

exports.createPlan = (req, res) => {
    let plan = new Plan();
    const {name} = req.body;
    if (!name ) {
        console.log(utils.generateServerErrorCode(res, 403, 'no name given', NAME_IS_EMPTY, 'createPlan'))
        return res.status(403).json(utils.generateServerErrorCode(res, 403, 'no name given', NAME_IS_EMPTY, 'createPlan'));
    }
    plan.name = name;
    plan.users = [req.user.userName];
    plan.owner = req.user.userName;
    plan.tasks = [];
    console.log(plan)
    plan.save((err) => {
        if (err) return res.json({success: false, error: err});
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
        if (!plan){return res.json({error: err})}

        Plan.deleteOne({_id: id}, (err) => {
            if (err) return res.send(err);
            Task.remove({plan: id}, (err, updatedPlan) => { if(err) {console.log(err)} })
            return res.json({success: true});
        });
    })
}
