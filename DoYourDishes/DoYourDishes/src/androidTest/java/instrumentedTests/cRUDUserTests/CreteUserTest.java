package instrumentedTests.cRUDUserTests;

import androidx.test.core.app.ActivityScenario;

import com.view.gui.LandingActivity;

import org.junit.Before;
import org.junit.Test;

import instrumentedTests.ShortcutEngine.IcRUDShortcut;
import instrumentedTests.ShortcutEngine.RandomStringGeneratorForLogin;
import instrumentedTests.ShortcutEngine.cRudEngine;

public class CreteUserTest {

    @Before
    public void launchActivity() {
        ActivityScenario.launch(LandingActivity.class);
    }

    RandomStringGeneratorForLogin rando = new RandomStringGeneratorForLogin();
    public final String USERNAME = rando.generateStringAndReturn() + "myName";
    public final String PASSWORD = rando.generateStringAndReturn() + "myPass";

    /**
     * Test erstelle einen User
     * erstelle einen User und lösche den User dann den User
     */
    @Test
    public void createUserGutTest1() {
        IcRUDShortcut icRUDShortcut;
        icRUDShortcut = new cRudEngine();

        icRUDShortcut.createUser(USERNAME, PASSWORD);
        icRUDShortcut.deletUser();
    }
}
