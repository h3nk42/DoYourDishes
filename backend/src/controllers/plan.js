const Plan = require('../models/Plan')
const Task = require('../models/Task')
const mongoose = require('mongoose');
const ObjectId = mongoose.Types.ObjectId;


exports.findPlanToOwner = (req, res) => {
    let owner = req.body.owner;

    if (!owner)
        return res.status(401).send({
            success: false,
            error: 'INVALID INPUTS',
        });

    Plan.find( {owner: owner} ,(err, data) => {
        if (err) {
            return res.json({success: false, error: err});
        } else {
            return res.json({success: true, data: data});
        }
    });
}

exports.findAllPlans = (req, res) => {
    //let {planId} = req.body;
    let planId = "5fa2ea499c0cacc6ca5b9f02";
    let objId = new ObjectId(planId);
    Plan.find((err, data) => {
        if (err) {
            return res.json({success: false, error: err});
        } else {
            return res.json({success: true, data: data});
        }
    });
}

exports.createPlan = (req, res) => {
    let plan = new Plan();
    const {name, messageSender } = req.body;
    if (!name || !messageSender) {
        return res.status(401).send({
            success: false,
            error: 'INVALID INPUTS',
        });
    }
    plan.name = name;
    plan.users = [messageSender];
    plan.owner = messageSender;
    plan.tasks = [];
    plan.save((err) => {
        if (err) return res.json({success: false, error: err});
        return res.json({success: true});
    });
}

exports.deletePlan = (req, res) => {
    const {id} = req.body;
    console.log(req.body);
    if (!id ) {
        return res.json(returnError("id is null"));
    }
    Plan.findById(id, (err, plan) => {
        if (!plan){return res.json(returnError("plan is null"));}

        Plan.deleteOne({_id: id}, (err) => {
            if (err) return res.send(err);
            Task.remove({plan: id}, (err, updatedPlan) => { if(err) {console.log(err)} })
            return res.json({success: true});
        });
    })
}
