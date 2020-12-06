const express = require('express');
const planControl = require('../controllers/plan')
const passport = require('passport')
const {body} = require('express-validator');


const router = express.Router();


router.get(
    '/findPlanToOwner',
    passport.authenticate('jwt',{session: false}),
    planControl.findPlanToOwner);

router.get(
    '/findAllPlans',
    planControl.findAllPlans);

router.post(
    '/createPlan',
    [body('name').not().isEmpty().withMessage('planName is required')],
    passport.authenticate('jwt',{session: false}),
    planControl.createPlan);

router.delete(
    '/deletePlan',
    [body('id').not().isEmpty().withMessage('planName is required')],
    passport.authenticate('jwt',{session: false}),
    planControl.deletePlan);

router.post(
    '/addUser',
    [
        body('userName').not().isEmpty().withMessage('Your username is required')],
        passport.authenticate('jwt',{session: false}),
    planControl.addUser )

module.exports = router;
