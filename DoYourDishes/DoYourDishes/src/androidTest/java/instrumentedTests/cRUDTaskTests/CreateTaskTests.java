package instrumentedTests.cRUDTaskTests;

import androidx.test.core.app.ActivityScenario;

import com.view.gui.LandingActivity;

import org.junit.Before;
import org.junit.Test;

import instrumentedTests.ShortcutEngine.CrudEngine;
import instrumentedTests.ShortcutEngine.ICrudShortcut;
import instrumentedTests.ShortcutEngine.RandomGenerator;
import instrumentedTests.ShortcutEngine.RandomGeneratorImpl;

public class CreateTaskTests {

    @Before
    public void launchActivity() {
        ActivityScenario.launch(LandingActivity.class);
    }

    RandomGenerator rando = new RandomGeneratorImpl();
    public final String USERNAME = rando.generateStringAndReturn() + "myName";
    public final String PASSWORD = rando.generateStringAndReturn() + "myPass";
    public final String PLAN = rando.generateStringAndReturn() + " Plan";
    public final String TASK1 = rando.generateStringAndReturn() + " Task1";
    public final String TASK2 = rando.generateStringAndReturn() + " Task2";
    public final String TASK3 = rando.generateStringAndReturn() + " Task3";
    public final String TASK4 = rando.generateStringAndReturn() + " Task4";
    public final String TASK5 = rando.generateStringAndReturn() + " Task5";

    public final int POINTS1 = rando.generateIntAndReturn();
    public final int POINTS2 = rando.generateIntAndReturn();
    public final int POINTS3 = rando.generateIntAndReturn();
    public final int POINTS4 = rando.generateIntAndReturn();
    public final int POINTS5 = rando.generateIntAndReturn();


    /**
     * Test erstelle einen Task
     */
    @Test
    public void createTaskGutTest1() {
        ICrudShortcut icRUDShortcut;
        icRUDShortcut = new CrudEngine();

        icRUDShortcut.createUser(USERNAME, PASSWORD);
        icRUDShortcut.createPlan(PLAN);
        icRUDShortcut.addTask(TASK1, POINTS1);
        icRUDShortcut.deletUser();
    }

    /**
     * Test markiere einen Task als Erledigt
     */
    @Test
    public void markAsDoneTaskGutTest1() {
        ICrudShortcut icRUDShortcut;
        icRUDShortcut = new CrudEngine();

        icRUDShortcut.createUser(USERNAME, PASSWORD);
        icRUDShortcut.createPlan(PLAN);
        icRUDShortcut.addTask(TASK1, POINTS1);
        icRUDShortcut.addTask(TASK2, POINTS2);
        icRUDShortcut.addTask(TASK3, POINTS3);

        icRUDShortcut.markAsDone1(TASK1);
        icRUDShortcut.clickRefresh();
        icRUDShortcut.markAsDone1(TASK2);
        icRUDShortcut.clickRefresh();
        icRUDShortcut.markAsDone1(TASK3);

        icRUDShortcut.deletUser();
    }


}
