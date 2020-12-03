const Plan = require('../models/Plan')
const Task = require('../models/Task')
const mongoose = require('mongoose');
const ObjectId = mongoose.Types.ObjectId;



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

exports.delSingleTask = (req, res) => {
    let taskId = req.body.taskId;

    if (!taskId) {
        return res.json({
            success: false,
            error: 'INVALID INPUTS',
        });
    }

    var planId = '';

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
            console.log(planId);
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
                                console.log(err)
                            } else {

                            }
                        })
                    return res.json({success: true, data: data});
                }
            })
        }
    })
}

exports.createTask = (req, res) => {
    let task = new Task();
    const {name, messageSender, planId } = req.body;

    if(!name || !messageSender || !planId )
        return res.status(400).json({
            success: false,
            err: 'wrong data given'
        })

    //checks if plan with planId exists
    Plan.exists({_id: planId}, (err, exists) => {
        console.log(exists)
        if (!name || !messageSender || !planId || !exists ) {
            return res.json({
                success: false,
                error: 'INVALID INPUTS',
            });
        }

        task.name = name;
        task.lastTimeDone = 0;
        task.plan = planId;
        task.save((err, newtask) => {
            if (err) return res.json({success: false, error: err});
            Plan.updateOne(
                { _id: planId },
                {
                    $push: {
                        tasks: [{taskName: name, taskId: newtask._id}]
                    }
                },
                (err, updatedPlan) => { if(err) {console.log(err)} } )
            return res.json({success: true});
        });
    });
}



