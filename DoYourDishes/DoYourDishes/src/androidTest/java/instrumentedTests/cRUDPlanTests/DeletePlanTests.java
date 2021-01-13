package instrumentedTests.cRUDPlanTests;

import androidx.test.core.app.ActivityScenario;

import com.view.gui.LandingActivity;

import org.junit.Before;
import org.junit.Test;

import instrumentedTests.ShortcutEngine.CrudEngine;
import instrumentedTests.ShortcutEngine.ICrudShortcut;
import instrumentedTests.ShortcutEngine.RandomGenerator;
import instrumentedTests.ShortcutEngine.RandomGeneratorImpl;

public class DeletePlanTests {

    @Before
    public void launchActivity() {
        ActivityScenario.launch(LandingActivity.class);
    }

    RandomGenerator rando = new RandomGeneratorImpl();
    public final String USERNAME = rando.generateStringAndReturn() + "myName";
    public final String PASSWORD = rando.generateStringAndReturn() + "myPass";
    public final String PLAN = rando.generateStringAndReturn() + " Plan";


    /**
     * Test erstelle einen Plan
     * erstelle einen User, erstelle einen Plan und lösche den Plan dann den User
     */
    @Test
    public void deletePlanGutTest1() {
        ICrudShortcut icRUDShortcut;
        icRUDShortcut = new CrudEngine();

        icRUDShortcut.createUser(USERNAME, PASSWORD);
        icRUDShortcut.createPlan(PLAN);
        icRUDShortcut.deletePlan();
        icRUDShortcut.deletUser();

    }
}
