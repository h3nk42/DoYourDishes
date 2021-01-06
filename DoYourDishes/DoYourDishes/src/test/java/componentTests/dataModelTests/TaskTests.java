package componentTests.dataModelTests;

import com.model.dataModel.Task;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

public class TaskTests {

    public final String TASKNAME = "cooking";
    public final String PLAN = "Our Houshold Plan";
    public final int POINTS = 50;
    public final BigInteger TIMEPASSED = BigInteger.valueOf(120); //seconds
    public final String TASKID = "123456-789";

    Task task;

    @Before
    public void erzeugeTask(){
        task = new Task(TASKNAME,PLAN,POINTS,TIMEPASSED, TASKID);
    }

    @Test(expected = NullPointerException.class)
    public void fehlerFallLeererKostruktor(){
        Task task2Fail = new Task();
    }

    @Test(expected = IllegalArgumentException.class)
    public void fehlerFallNullPunkte(){
        task.setPointsWorth(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fehlerFallNegativePunktzahl(){
        task.setPointsWorth(-100);
    }

    @Test
    public void gutBigTest(){
        task.setName("vacuuming");

        Assert.assertEquals(PLAN,task.getPlan());
        Assert.assertEquals(TIMEPASSED,task.getLastTimeDone());
        Assert.assertEquals("vacuuming",task.getName());
    }


}
