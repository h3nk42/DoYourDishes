package LogicTest;


import com.control.logic.Crud;
import com.control.logic.TaskLogicInterface;
import com.control.logic.UserLogicInterface;
import com.control.logic.Plan;

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
        TaskLogicInterface task1 = new Crud();
        Plan plan = new Plan();
        task1.create(12,"vonPlan1", plan);
        //TODO Assertion Abfrage
    }


    /**
     * test ob ein User erstellt werden konnte
     */
    @Test
    public void gutTestUser(){
        UserLogicInterface user1 = new Crud();


    }

    @Test
    public void gutTestDeletePlan(){

    }


}
