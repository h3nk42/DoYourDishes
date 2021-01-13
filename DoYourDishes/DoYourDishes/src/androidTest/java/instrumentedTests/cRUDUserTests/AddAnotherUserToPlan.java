package instrumentedTests.cRUDUserTests;

import androidx.test.core.app.ActivityScenario;

import com.view.gui.LandingActivity;

import org.junit.Before;
import org.junit.Test;

import instrumentedTests.ShortcutEngine.CrudEngine;
import instrumentedTests.ShortcutEngine.ICrudShortcut;
import instrumentedTests.ShortcutEngine.RandomGenerator;
import instrumentedTests.ShortcutEngine.RandomGeneratorImpl;

public class AddAnotherUserToPlan {

    RandomGenerator rando = new RandomGeneratorImpl();
    public final String USERNAME1 = rando.generateStringAndReturn() + "User1";
    public final String PASSWORD1 = rando.generateStringAndReturn() + "myPass1";
    public final String USERNAME2 = rando.generateStringAndReturn() + "User2";
    public final String PASSWORD2 = rando.generateStringAndReturn() + "myPass2";
    public final String USERNAME3 = rando.generateStringAndReturn() + "User3";
    public final String PASSWORD3 = rando.generateStringAndReturn() + "myPass3";
    public final String USERNAME4 = rando.generateStringAndReturn() + "User4";
    public final String PASSWORD4 = rando.generateStringAndReturn() + "myPass4";
    public final String PLAN = "Plan2Gether";
    public final String TASK = "Entwurf";
    public final int POINTS = rando.generateIntAndReturn();

    @Before
    public void launchActivity() {
        ActivityScenario.launch(LandingActivity.class);
    }

    @Test
    public void addAnotherUserToYourPlanGutTest1() {
        ICrudShortcut icRUDShortcut;
        icRUDShortcut = new CrudEngine();

        icRUDShortcut.createUser(USERNAME1, PASSWORD1);
        icRUDShortcut.pressHardwareButtonBack();

        icRUDShortcut.createUser(USERNAME3, PASSWORD3);
        icRUDShortcut.pressHardwareButtonBack();

        icRUDShortcut.createUser(USERNAME4, PASSWORD4);
        icRUDShortcut.pressHardwareButtonBack();


        icRUDShortcut.createUser(USERNAME2, PASSWORD2);
        icRUDShortcut.createPlan(PLAN);
        icRUDShortcut.addUserToPlan(USERNAME1);
        icRUDShortcut.addUserToPlan(USERNAME3);
        icRUDShortcut.addUserToPlan(USERNAME4);
        icRUDShortcut.pressHardwareButtonBack();


        icRUDShortcut.login(USERNAME1, PASSWORD1);
        icRUDShortcut.clickOnPlan();
        icRUDShortcut.clickOnUsersTab();
        icRUDShortcut.pressHardwareButtonBack();

        launchActivity();

        icRUDShortcut.login(USERNAME2, PASSWORD2);
        icRUDShortcut.deletUser();

        launchActivity();

        icRUDShortcut.login(USERNAME1, PASSWORD1);
        icRUDShortcut.deletUser();

        launchActivity();

        icRUDShortcut.login(USERNAME3, PASSWORD3);
        icRUDShortcut.deletUser();

        launchActivity();

        icRUDShortcut.login(USERNAME4, PASSWORD4);
        icRUDShortcut.deletUser();
    }


    /**
     * Füge anderen Nutzer dem Plan hinzu
     */
    @Test
    public void addAnotherUserToYourPlanGutTest2() {

        ICrudShortcut icRUDShortcut;
        icRUDShortcut = new CrudEngine();

        icRUDShortcut.createUser(USERNAME1, PASSWORD1);
        icRUDShortcut.pressHardwareButtonBack();

        icRUDShortcut.createUser(USERNAME2, PASSWORD2);
        icRUDShortcut.createPlan(PLAN);
        icRUDShortcut.addUserToPlan(USERNAME1);
        icRUDShortcut.pressHardwareButtonBack();

        icRUDShortcut.login(USERNAME1, PASSWORD1);
        icRUDShortcut.addTask(TASK, POINTS);
        icRUDShortcut.pressHardwareButtonBack();

        icRUDShortcut.login(USERNAME2, PASSWORD2);
        icRUDShortcut.clickOnPlan();
        icRUDShortcut.markAsDone(TASK);
        icRUDShortcut.clickOnUsersTab();
        icRUDShortcut.removeUser(USERNAME1);
        icRUDShortcut.pressHardwareButtonBack();
        icRUDShortcut.deletePlan();
        icRUDShortcut.deletUser();

        launchActivity();
        icRUDShortcut.login(USERNAME1, PASSWORD1);
        icRUDShortcut.deletUser();
    }


}
