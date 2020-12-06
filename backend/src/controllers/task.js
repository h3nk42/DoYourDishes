const Plan = require('../models/Plan')
const Task = require('../models/Task')
const mongoose = require('mongoose');
const User = require("../models/User");
const ObjectId = mongoose.Types.ObjectId;
const utils = require('../utils/index')
const {validationResult} = require("express-validator");



exports.findAllTasks = (req, res) => {
    Task.find((err, data) => {
        if (err) {
            return res.json({success: false, error: err});
        } else {
            return res.json({success: true, data: data});
        }
    });
}

exports.delTasks = (req, res) => {
    let planId = req.body.planId;

    if (!planId) {
        return res.status(400).json({
            success: false,
            error: 'INVALID INPUTS',
        });
    }

    Task.deleteMany({ plan: planId}, (err, data) => {
        if (err) {
            return res.status(400).json({success: false, error: err});
        } else {
            Plan.updateOne(
                { _id: planId },
                {
                    tasks: []
                },
                (err, updatedPlan) => { if(err) {console.log(err)} else {console.log(updatedPlan)}} )
            return res.json({success: true, data: data});
        }
    });
}

exports.delSingleTask = async (req, res) => {
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        return res.status(400).json({ errors: errors.array() });
    }
    let msgSender = req.user.userName;
    let taskId = req.body.taskId;

    //eigentlich unnoetige abfrage
    let user = await User.findOne({userName: msgSender})
    if (user.plan === null ) return res.status(411).json(utils.generateServerErrorCode(res, 400, 'User has no Plan', 'usr no plan','delTask'))
    planId = user.plan

    let task = await Task.findOne({_id: taskId}, (err, task) => {})
    if (!task) return res.status(411).json(utils.generateServerErrorCode(res, 400, 'Task not found', 'tskNotFound','delTask'))

    let plan = await Plan.findOne({_id: task.plan}, (err, plan) => {})
    if(!plan.users.filter(e=>e.userName === msgSender))  return res.status(412).json(utils.generateServerErrorCode(res, 400, 'User not part of Plan the task is in', 'usr not member or plan','delTask'))


    Task.findOne({_id: taskId}, (err, data) => {
        if(err)
            return res.json({
                success: false,
                error: 'INVALID INPUTS',
            });
        if (!data)
            return res.json({
                success: false,
                error: 'Task not found',
            });
        else {
            planId = data.plan;

            Task.deleteOne({_id: taskId}, (err, data) => {
                if (err) {
                    return res.json({success: false, error: err});
                } else {
                    Plan.updateOne(
                        {_id: planId},
                        {
                            $pull: {
                                // omg man muss hier auf ObjectId casten sonst vergleicht er falsch..
                                tasks: {taskId: ObjectId(taskId)}
                            }
                        },
                        (err, updatedPlan) => {
                            if (err) {

                            } else {

                            }
                        })
                    return res.json({success: true, data: data});
                }
            })
        }
    })
}

exports.createTask = async (req, res) => {
    if(!req.user) return res.status(410).json(utils.generateServerErrorCode(res, 400, 'No userName from Token found', 'tokenProblem','createTask'))

    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        return res.status(410).json(utils.generateServerErrorCode(res, 400, 'wrong inputs', 'inputProblem','createTask'));
    }

    let task = new Task();
    const {name, pointsWorth} = req.body;
    let msgSender = req.user.userName;


    let user = await User.findOne({userName: msgSender})
    if (user.plan === null ) return res.status(411).json(utils.generateServerErrorCode(res, 400, 'User has no Plan', 'usr no plan','createTask'))
    planId = user.plan
    let plan = await Plan.findOne({_id: planId}, (err, plan) => {

    })
    //checks if plan with planId exists
    Plan.exists({_id: planId }, (err, exists) => {
        task.name = name;
        task.lastTimeDone = Date.now();
        task.plan = planId;
        task.pointsWorth = pointsWorth
        task.save((err, newtask) => {
            if (err) return res.json({success: false, error: err});
            Plan.updateOne(
                { _id: planId },
                {
                    $push: {
                        tasks: [{taskName: name, taskId: newtask._id, pointsWorth: pointsWorth}]
                    }
                },
                (err, updatedPlan) => { if(err) {console.log(err)} } )
            return res.json({success: true});
        });
    });
}


exports.fulfillTask = async (req, res) => {
    if(!req.user) return res.status(410).json(utils.generateServerErrorCode(res, 400, 'No userName from Token found', 'tokenProblem','fullFillTask'))
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        return res.status(410).json(utils.generateServerErrorCode(res, 400, 'wrong inputs', 'inputProblem','fullFillTask'));
    }

    let {taskId} = req.body;
    let msgSender = req.user.userName;

    let user = await User.findOne({userName: msgSender})
    if (user.plan === null ) return res.status(411).json(utils.generateServerErrorCode(res, 400, 'User has no Plan', 'usr no plan','fullfillTask'))
    planId = user.plan

    let task = await Task.findOne({_id: taskId}, (err, task) => {})
    if (!task) return res.status(411).json(utils.generateServerErrorCode(res, 400, 'Task not found', 'tskNotFound','delTask'))

    let plan = await Plan.findOne({_id: task.plan}, (err, plan) => {})
    if(!plan.users.filter(e=>e.userName === msgSender))  return res.status(412).json(utils.generateServerErrorCode(res, 400, 'User not part of Plan the task is in', 'usr not member or plan','delTask'))

    Task.updateOne({_id: taskId},{$set:{lastTimeDone: Date.now()}}, (err, data) => {
        if (err) return res.status(420).json(utils.generateServerErrorCode(res, 400, 'mongoDb error', err,'delTask'))

    })
    //increment points in user array on plan
    Plan.updateOne({_id: planId, users: {$elemMatch: {userName: msgSender}}}, {$inc: {"users.$.points": task.pointsWorth }}, (err, data) => {
        if (err) return res.status(420).json(utils.generateServerErrorCode(res, 400, 'mongoDb error', err,'delTask'))
        return res.json({success: true});
    })
}


