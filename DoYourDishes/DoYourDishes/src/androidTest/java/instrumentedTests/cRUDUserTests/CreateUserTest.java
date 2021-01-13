package instrumentedTests.cRUDUserTests;

import androidx.test.core.app.ActivityScenario;

import com.view.gui.LandingActivity;

import org.junit.Before;
import org.junit.Test;

import instrumentedTests.ShortcutEngine.CrudEngine;
import instrumentedTests.ShortcutEngine.ICrudShortcut;
import instrumentedTests.ShortcutEngine.RandomGenerator;
import instrumentedTests.ShortcutEngine.RandomGeneratorImpl;

public class CreateUserTest {

    @Before
    public void launchActivity() {
        ActivityScenario.launch(LandingActivity.class);
    }

    RandomGenerator rando = new RandomGeneratorImpl();
    public final String USERNAME = rando.generateStringAndReturn() + "myName";
    public final String PASSWORD = rando.generateStringAndReturn() + "myPass";

    public final String USERNAME1 = rando.generateStringAndReturn() + "User1";
    public final String PASSWORD1 = rando.generateStringAndReturn() + "myPass1";
    public final String USERNAME2 = rando.generateStringAndReturn() + "User2";
    public final String PASSWORD2 = rando.generateStringAndReturn() + "myPass2";
    public final String USERNAME3 = rando.generateStringAndReturn() + "User3";
    public final String PASSWORD3 = rando.generateStringAndReturn() + "myPass3";
    public final String USERNAME4 = rando.generateStringAndReturn() + "User4";
    public final String PASSWORD4 = rando.generateStringAndReturn() + "myPass4";
    public final String PLAN = "Plan2Gether";

    public static final String EXISTINGUSER = "MAXMUSTERMANN";
    public static final String EXISTINGPASS = "MUSTERMANN";

    /**
     * Registriere Nutzer
     * erstelle einen User und lösche den User dann den User
     */
    @Test
    public void createUserGutTest1() {
        ICrudShortcut icRUDShortcut;
        icRUDShortcut = new CrudEngine();

        icRUDShortcut.createUser(USERNAME, PASSWORD);
        icRUDShortcut.deletUser();
    }

    /**
     * Login Nutzer
     * erstelle einen User und lösche den User dann den User
     */
    @Test
    public void loginUserGutTest1() {
        ICrudShortcut icRUDShortcut;
        icRUDShortcut = new CrudEngine();

        icRUDShortcut.createUser(USERNAME, PASSWORD);
        icRUDShortcut.pressHardwareButtonBack();
        icRUDShortcut.login(USERNAME, PASSWORD);
        icRUDShortcut.deletUser();
    }

    /**
     * Login eines bestehenden Nutzers
     * ACHTUNG diesen Nutzer nicht löschen!
     */
    @Test
    public void LogInExistingUser() {
        ICrudShortcut icRUDShortcut;
        icRUDShortcut = new CrudEngine();
        icRUDShortcut.login(EXISTINGUSER, EXISTINGPASS);
    }

    /**
     * Lösche Nutzer
     * Erstelle Nutzer und lösche ihn danach
     * <p>
     * Test läuft zwar durch gilt aber als nicht bestanden, da Login nach löschen des Accounts möglich
     */
    @Test
    public void deleteUserGutTest1() {
        ICrudShortcut icRUDShortcut;
        icRUDShortcut = new CrudEngine();

        icRUDShortcut.createUser(USERNAME, PASSWORD);
        icRUDShortcut.deletUser();

        launchActivity();
        icRUDShortcut.login(USERNAME, PASSWORD);
    }

    /**
     * Lösche anderen Nutzer aus dem Plan
     * Erstelle Nutzer 1,2,3,4,5; Login Nutzer 1 füge alle anderen hinzu
     * Entferne alle Nutzer aus dem Plan und lösche jeden Account einzeln
     */
    @Test
    public void deleteUserOffOfPlan() {
        ICrudShortcut icRUDShortcut;
        icRUDShortcut = new CrudEngine();

        icRUDShortcut.createUser(USERNAME, PASSWORD);
        icRUDShortcut.createPlan(PLAN);
        icRUDShortcut.pressHardwareButtonBack();

        icRUDShortcut.createUser(USERNAME1, PASSWORD1);
        icRUDShortcut.pressHardwareButtonBack();

        icRUDShortcut.createUser(USERNAME2, PASSWORD2);
        icRUDShortcut.pressHardwareButtonBack();

        icRUDShortcut.createUser(USERNAME3, PASSWORD3);
        icRUDShortcut.pressHardwareButtonBack();

        icRUDShortcut.createUser(USERNAME4, PASSWORD4);
        icRUDShortcut.pressHardwareButtonBack();

        icRUDShortcut.login(USERNAME, PASSWORD);
        icRUDShortcut.clickOnPlan();
        icRUDShortcut.clickOnUsersTab();
        icRUDShortcut.addUserToPlan1(USERNAME1);
        icRUDShortcut.addUserToPlan1(USERNAME2);
        icRUDShortcut.addUserToPlan1(USERNAME3);
        icRUDShortcut.addUserToPlan1(USERNAME4);
        icRUDShortcut.pressHardwareButtonBack();
        icRUDShortcut.clickRefresh();
        icRUDShortcut.clickOnPlan();
        icRUDShortcut.clickOnUsersTab();

        icRUDShortcut.removeUser(USERNAME1);
        icRUDShortcut.removeUser(USERNAME3);
        icRUDShortcut.removeUser(USERNAME2);
        icRUDShortcut.removeUser(USERNAME4);

        icRUDShortcut.pressHardwareButtonBack();
        icRUDShortcut.pressHardwareButtonBack();


        icRUDShortcut.login(USERNAME1, PASSWORD1);
        icRUDShortcut.deletUser();

        launchActivity();

        icRUDShortcut.login(USERNAME2, PASSWORD2);
        icRUDShortcut.deletUser();

        launchActivity();

        icRUDShortcut.login(USERNAME3, PASSWORD3);
        icRUDShortcut.deletUser();

        launchActivity();

        icRUDShortcut.login(USERNAME4, PASSWORD4);
        icRUDShortcut.deletUser();

        launchActivity();

        icRUDShortcut.login(USERNAME, PASSWORD);
        icRUDShortcut.deletUser();

    }

}
