package LogicTest;


import com.control.logic.TaskControllerInterface;
import com.control.logic.UserControllerInterface;

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
        TaskControllerInterface task1 = new Crud();
        Plan plan = new Plan();
        task1.create(12,"vonPlan1", plan);
        //TODO Assertion Abfrage
    }


    /**
     * test ob ein User erstellt werden konnte
     */
    @Test
    public void gutTestUser(){
        UserControllerInterface user1 = new Crud();


    }

    @Test
    public void gutTestDeletePlan(){

    }


}
