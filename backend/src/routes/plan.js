const express = require('express');
const planControl = require('../controllers/plan')

const router = express.Router();

router.get('/findPlanToOwner', planControl.findPlanToOwner);
router.get('/findAllPlans', planControl.findAllPlans);
router.post('/createPlan', planControl.createPlan);
router.delete('/deletePlan', planControl.deletePlan);

function returnError(errmsg) {
    return {
        success: false,
        error: {errmsg},
    }
}


module.exports = router;
