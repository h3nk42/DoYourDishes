package instrumentedTests.cRUDTaskTests;

import androidx.test.core.app.ActivityScenario;

import com.view.gui.LandingActivity;

import org.junit.Before;
import org.junit.Test;

import instrumentedTests.ShortcutEngine.CrudEngine;
import instrumentedTests.ShortcutEngine.ICrudShortcut;
import instrumentedTests.ShortcutEngine.RandomGenerator;
import instrumentedTests.ShortcutEngine.RandomGeneratorImpl;

public class DeleteTaskTests {

    @Before
    public void launchActivity() {
        ActivityScenario.launch(LandingActivity.class);
    }

    RandomGenerator rando = new RandomGeneratorImpl();
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


    /**
     * Lösche Task nachdem er erledigt wurde
     * addTask;markAsDone;deleteTask mit HW Button explizit
     */
    @Test
    public void deleteTaskGutTest3() {
        ICrudShortcut icRUDShortcut;
        icRUDShortcut = new CrudEngine();

        icRUDShortcut.createUser(USERNAME, PASSWORD);
        icRUDShortcut.createPlan(PLAN);
        icRUDShortcut.addTask1(TASK1, POINTS1);
        icRUDShortcut.pressHardwareButtonBack();
        icRUDShortcut.addTask1(TASK2, POINTS2);
        icRUDShortcut.pressHardwareButtonBack();
        icRUDShortcut.addTask1(TASK3, POINTS3);
        icRUDShortcut.pressHardwareButtonBack();
        icRUDShortcut.addTask1(TASK4, POINTS4);


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


    /**
     * erstelle Task und lösche gleich danach
     */
    @Test
    public void deleteTaskGutTest2() {
        ICrudShortcut icRUDShortcut;
        icRUDShortcut = new CrudEngine();

        icRUDShortcut.createUser(USERNAME, PASSWORD);
        icRUDShortcut.createPlan(PLAN);
        icRUDShortcut.addTask1(TASK1, POINTS1);
        icRUDShortcut.deleteTask(TASK1);
        icRUDShortcut.pressHardwareButtonBack();
        icRUDShortcut.addTask1(TASK2, POINTS2);
        icRUDShortcut.deleteTask(TASK2);
        icRUDShortcut.pressHardwareButtonBack();
        icRUDShortcut.deletePlan();
        icRUDShortcut.deletUser();

    }


    /**
     * Test erstelle einen Plan
     * erstelle einen User, erstelle einen Plan und lösche den Plan dann den User
     */


    @Test
    public void deleteTaskGutTest1() {
        ICrudShortcut icRUDShortcut;
        icRUDShortcut = new CrudEngine();

        icRUDShortcut.createUser(USERNAME, PASSWORD);
        icRUDShortcut.createPlan(PLAN);
        icRUDShortcut.addTask(TASK1, POINTS1);
        icRUDShortcut.addTask(TASK2, POINTS2);
        icRUDShortcut.addTask(TASK3, POINTS3);
        icRUDShortcut.addTask(TASK4, POINTS4);
        icRUDShortcut.clickOnPlan();
        icRUDShortcut.deleteTask(TASK3);
        icRUDShortcut.deleteTask(TASK2);
        icRUDShortcut.deleteTask(TASK1);
        icRUDShortcut.deleteTask(TASK4);
        icRUDShortcut.pressHardwareButtonBack();
        icRUDShortcut.deletePlan();
        icRUDShortcut.deletUser();

    }


}
