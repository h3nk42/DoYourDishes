package instrumentedTests.cRUDTaskTests;

import androidx.test.core.app.ActivityScenario;

import com.view.gui.LandingActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import instrumentedTests.ShortcutEngine.IcRUDShortcut;
import instrumentedTests.ShortcutEngine.RandomStringGeneratorForLogin;
import instrumentedTests.ShortcutEngine.cRudEngine;

public class DeleteTaskTests {

    @Before
    public void launchActivity() {
        ActivityScenario.launch(LandingActivity.class);
    }

    RandomStringGeneratorForLogin rando = new RandomStringGeneratorForLogin();
    public final String USERNAME = rando.generateStringAndReturn() + "myName";
    public final String PASSWORD = rando.generateStringAndReturn() + "myPass";
    public final String PLAN = rando.generateStringAndReturn() + " Plan";

    public final String TASK1 = "firstTask";
    public final int POINTS1 = rando.generateIntAndReturn();

    public final String TASK2 = "secondTask";
    public final int POINTS2 = rando.generateIntAndReturn();

    public final String TASK3 = "thirdTask";
    public final int POINTS3 = rando.generateIntAndReturn();

    public final String TASK4 = "fourthTask";
    public final int POINTS4 = rando.generateIntAndReturn();


    @Test
    public void deleteTaskGutTest3(){
        IcRUDShortcut icRUDShortcut;
        icRUDShortcut = new cRudEngine();

        icRUDShortcut.createUser(USERNAME, PASSWORD);
        icRUDShortcut.createPlan(PLAN);
        icRUDShortcut.addTask1(TASK1,POINTS1);
        icRUDShortcut.pressHardwareButtonBack();
        icRUDShortcut.addTask1(TASK2,POINTS2);
        icRUDShortcut.pressHardwareButtonBack();
        icRUDShortcut.addTask1(TASK3,POINTS3);
        icRUDShortcut.pressHardwareButtonBack();
        icRUDShortcut.addTask1(TASK4,POINTS4);


        icRUDShortcut.markAsDone(TASK1);
        icRUDShortcut.markAsDone(TASK2);
        icRUDShortcut.markAsDone(TASK3);
        icRUDShortcut.markAsDone(TASK4);

        icRUDShortcut.deleteTask(TASK3);
        icRUDShortcut.deleteTask(TASK1);
        icRUDShortcut.deleteTask(TASK2);
        icRUDShortcut.deleteTask(TASK4);
        icRUDShortcut.pressHardwareButtonBack();
        icRUDShortcut.deletePlan();
        icRUDShortcut.deletUser();
    }



    //TODO deleteTask anpassen an TASKNAME
    //läuft durch

    @Test
    public void deleteTaskGutTest2(){
        IcRUDShortcut icRUDShortcut;
        icRUDShortcut = new cRudEngine();

        icRUDShortcut.createUser(USERNAME, PASSWORD);
        icRUDShortcut.createPlan(PLAN);
        icRUDShortcut.addTask1(TASK1,POINTS1);
        icRUDShortcut.deleteTask(TASK1);
        icRUDShortcut.pressHardwareButtonBack();
        icRUDShortcut.addTask1(TASK2,POINTS2);
        icRUDShortcut.deleteTask(TASK2);
        icRUDShortcut.pressHardwareButtonBack();
        icRUDShortcut.deletePlan();
        icRUDShortcut.deletUser();

    }



    /**
     * Test erstelle einen Plan
     * erstelle einen User, erstelle einen Plan und lösche den Plan dann den User
     */

    //TODO HW Buttonclick ein/aus-bauen in addTask


    @Test
    public void deleteTaskGutTest1() {
        IcRUDShortcut icRUDShortcut;
        icRUDShortcut = new cRudEngine();

        icRUDShortcut.createUser(USERNAME, PASSWORD);
        icRUDShortcut.createPlan(PLAN);
        icRUDShortcut.addTask(TASK1,POINTS1);
        icRUDShortcut.addTask(TASK2,POINTS2);
        icRUDShortcut.addTask(TASK3,POINTS3);
        icRUDShortcut.addTask1(TASK4,POINTS4);
        icRUDShortcut.deleteTask(TASK3);
        icRUDShortcut.deleteTask(TASK2);
        icRUDShortcut.deleteTask(TASK1);
        icRUDShortcut.deleteTask(TASK4);
        icRUDShortcut.pressHardwareButtonBack();
        icRUDShortcut.deletePlan();
        icRUDShortcut.deletUser();

    }







}
