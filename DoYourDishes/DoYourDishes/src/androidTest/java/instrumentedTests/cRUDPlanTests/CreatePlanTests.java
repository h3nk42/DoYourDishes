package instrumentedTests.cRUDPlanTests;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import com.view.gui.LandingActivity;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import instrumentedTests.ShortcutEngine.IcRUDShortcut;
import instrumentedTests.ShortcutEngine.RandomStringGeneratorForLogin;
import instrumentedTests.ShortcutEngine.cRudEngine;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class CreatePlanTests {

    RandomStringGeneratorForLogin rando = new RandomStringGeneratorForLogin();
    public final String USERNAME = rando.generateStringAndReturn() + "myName";
    public final String PASSWORD = rando.generateStringAndReturn() + "myPass";
    public final String PLAN = rando.generateStringAndReturn() + " Plan";

    @Before
    public void launchActivity() {
        ActivityScenario.launch(LandingActivity.class);
    }

    /**
     * Test erstelle einen Plan
     * erstelle einen User, erstelle einen Plan und lösche den User
     */
    @Ignore
    @Test
    public void createPlanGutTest2() {

        IcRUDShortcut icRUDShortcut;
        icRUDShortcut = new cRudEngine();

        icRUDShortcut.createUser(USERNAME, PASSWORD);
        icRUDShortcut.createPlan(PLAN);
        icRUDShortcut.deletUser();

    }
}
