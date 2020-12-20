package instrumentedTests.cRUDTaskTests;

import org.junit.Test;

import instrumentedTests.ShortcutEngine.IcRUDShortcut;
import instrumentedTests.ShortcutEngine.RandomStringGeneratorForLogin;
import instrumentedTests.ShortcutEngine.cRudEngine;

public class CreateTaskTests {

    RandomStringGeneratorForLogin rando = new RandomStringGeneratorForLogin();
    public final String USERNAME = rando.generateStringAndReturn() + "myName";
    public final String PASSWORD = rando.generateStringAndReturn() + "myPass";
    public final String PLAN = rando.generateStringAndReturn() + " Plan";
    public final String TASK = rando.generateStringAndReturn() + " Task";
    private final int POINTS = 22;

    /**
     * Test erstelle einen Task
     */
    @Test
    public void createTaskGutTest1() {
        IcRUDShortcut icRUDShortcut;
        icRUDShortcut = new cRudEngine();

        icRUDShortcut.createUser(USERNAME, PASSWORD);
        icRUDShortcut.createPlan(PLAN);
        icRUDShortcut.addTask(TASK, POINTS);
        icRUDShortcut.deletUser();
    }
}
