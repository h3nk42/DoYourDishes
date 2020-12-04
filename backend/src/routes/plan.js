const express = require('express');
const planControl = require('../controllers/plan')
const passport = require('passport')

const router = express.Router();


router.get('/findPlanToOwner', passport.authenticate('jwt',{session: false}), planControl.findPlanToOwner);
router.get('/findAllPlans', planControl.findAllPlans);
router.post('/createPlan', passport.authenticate('jwt',{session: false}), planControl.createPlan);
router.delete('/deletePlan', passport.authenticate('jwt',{session: false}), planControl.deletePlan);

function returnError(errmsg) {
    return {
        success: false,
        error: {errmsg},
    }
}


module.exports = router;
