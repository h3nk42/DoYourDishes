package LogicTest;


import com.control.logic.CrudTask;
import com.control.logic.CrudTaskInterface;
import com.control.logic.Plan;

import org.junit.Assert;
import org.junit.Test;

public class ControlTest {


    @Test
    public void gutTestCreatePlan(){

    }

    @Test
    public void gutTestTask(){

    }

    @Test
    public void gutTestCreateTask(){
        CrudTaskInterface task1 = new CrudTask();
        Plan plan = new Plan();
        task1.create(12,"vonPlan1", plan);
        //TODO Assertion Abfrage
    }


    @Test
    public void gutTestUser(){

    }

    @Test
    public void gutTestDeletePlan(){

    }


}
