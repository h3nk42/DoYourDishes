const express = require('express');
const Plan = require('../models/Plan')
const Task = require('../models/Task')
const mongoose = require('mongoose');
const ObjectId = mongoose.Types.ObjectId;

const taskControl = require('../controllers/task')

const router = express.Router();

router.get('/findAllTasks', taskControl.findAllTasks);
router.delete('/delTasks', taskControl.delTasks);
router.delete('/delSingleTask', taskControl.delSingleTask);
router.post('/createTask', taskControl.createTask);


module.exports = router;
