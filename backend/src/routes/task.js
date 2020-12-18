const passport = require("passport");

const express = require('express');

const taskControl = require('../controllers/task')
const {body} = require("express-validator");

const router = express.Router();

router.get('/findAllTasks', taskControl.findAllTasks);

router.delete('/delTasks', taskControl.delTasks);

router.delete('/delSingleTask',
    [
        body('taskId').not().isEmpty().withMessage('TaskId is required')
    ],
    passport.authenticate('jwt',{session: false}),
    taskControl.delSingleTask);

router.post('/createTask',
    [
        body('name').not().isEmpty().withMessage('TaskName is required'),
        body('name').isLength({min:1, max:18}),
        body('pointsWorth').not().isEmpty().withMessage('pointsWorth is required'),
        body('pointsWorth').isFloat({min: 1, max: 100 }).withMessage('pointsWorth is required')
    ],
    passport.authenticate('jwt',{session: false}),
    taskControl.createTask);

router.post('/fulfillTask',
    [
        body('taskId').not().isEmpty().withMessage('taskId is required')
    ],
    passport.authenticate('jwt',{session: false}),
    taskControl.fulfillTask);


module.exports = router;
