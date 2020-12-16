package com.control.asyncLogic;

/**
 * this interface works with the asynchronous fetchPlan task
 *
 *  every activity can implement its own version of the interface to handle what is done with the response Values
 *
 */
public interface FetchPlanCallBackInterface {

    /**
     *  is called by the postExecute method of fetchPlan asyncTask and receives a String[] with 1 - 3 values
     * @param planData loginData[0] is always status of request (error or success) loginData[1] is planName loginData[2] is planOwner
     */
    void fetchPlanCallBack(String[] planData);

    /**
     *  is called when the asyncTask fetch plan should be started/executed
     */
    void fetchPlanCallAsync();
}
