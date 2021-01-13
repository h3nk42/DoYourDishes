/**
 * Testet ob ein Nutzer registriert werden kann
 */

package instrumentedTests.cRUDUserTests;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.control.controllerLogic.DebugState;
import com.view.R;
import com.view.gui.LandingActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import instrumentedTests.ShortcutEngine.RandomGenerator;
import instrumentedTests.ShortcutEngine.RandomGeneratorImpl;
import instrumentedTests.ShortcutEngine.ToastMatcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreateUserTestsWithoutShortcutEngine {
    DebugState state;
    RandomGenerator rando = new RandomGeneratorImpl();
    public final String USERNAME_STRING_TO_BE_TYPED = rando.generateStringAndReturn() + "henk";
    public final String PASSWORD_STRING_TO_BE_TYPED = rando.generateStringAndReturn() + "hotrod";
    public final String PLAN_STRING_TO_BE_TYPED = "EspressoPlan1";


    @Before
    public void launchActivity() {
        ActivityScenario.launch(LandingActivity.class);
    }

    // einen User registrieren
    @Test
    public void createUserGutTest1() {
        onView(withId(R.id.toCreateAccountActivityButton)).perform(click());

        // gib Username ein
        onView(withId(R.id.registerUserNameTextField))
                .perform(typeText(USERNAME_STRING_TO_BE_TYPED), closeSoftKeyboard());

        // gib password ein
        onView(withId(R.id.passwordTextField))
                .perform(typeText(PASSWORD_STRING_TO_BE_TYPED), closeSoftKeyboard());

        // gib confirm password ein
        onView(withId(R.id.confirmRegisterPasswordEditText))
                .perform(typeText(PASSWORD_STRING_TO_BE_TYPED), closeSoftKeyboard());

        // klicke auf register
        onView(withId(R.id.registerButton)).perform(click());

        //deleteUser
        Assert.assertNotSame(state, DebugState.LOGGED_IN);
    }

    /**
     * Username zu kurz
     * ein User registriert sich mit falscher Usernamelänge
     */
    @Test
    public void creatUserSchlechtTest1() {
        onView(withId(R.id.toCreateAccountActivityButton)).perform(click());

        // gib Username mit 2 Buchstaben ein
        onView(withId(R.id.registerUserNameTextField))
                .perform(typeText("aa"), closeSoftKeyboard());

        // gib password ein
        onView(withId(R.id.passwordTextField))
                .perform(typeText(PASSWORD_STRING_TO_BE_TYPED), closeSoftKeyboard());

        // gib confirm password ein
        onView(withId(R.id.confirmRegisterPasswordEditText))
                .perform(typeText(PASSWORD_STRING_TO_BE_TYPED), closeSoftKeyboard());

        // klicke auf register
        onView(withId(R.id.registerButton)).perform(click());

        //Prüfe Toast
        onView(withText("username too short")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        //deleteUser
        Assert.assertNotSame(state, DebugState.NOT_LOGGED_IN);

    }


    /**
     * Passwort zu kurz
     * ein User registriert sich mit falscher Passwortlänge, gibt 2 Zahlen ein
     */
    @Test
    public void creatUserSchlechtTest2() {
        onView(withId(R.id.toCreateAccountActivityButton)).perform(click());

        // gib Username mit 2 Buchstaben ein
        onView(withId(R.id.registerUserNameTextField))
                .perform(typeText(USERNAME_STRING_TO_BE_TYPED), closeSoftKeyboard());

        // gib password ein
        onView(withId(R.id.passwordTextField))
                .perform(typeText("12"), closeSoftKeyboard());

        // gib confirm password ein
        onView(withId(R.id.confirmRegisterPasswordEditText))
                .perform(typeText("12"), closeSoftKeyboard());

        // klicke auf register
        onView(withId(R.id.registerButton)).perform(click());

        //Prüfe Toast
        onView(withText("password too short")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        //deleteUser
        Assert.assertNotSame(state, DebugState.NOT_LOGGED_IN);

    }

    /**
     * Passwort stimmt nicht überein
     * ein User registriert sich mit falscher confirmationpass
     */
    @Test
    public void creatUserSchlechtTest3() {
        onView(withId(R.id.toCreateAccountActivityButton)).perform(click());

        // gib Username mit 2 Buchstaben ein
        onView(withId(R.id.registerUserNameTextField))
                .perform(typeText(USERNAME_STRING_TO_BE_TYPED), closeSoftKeyboard());

        // gib password ein
        onView(withId(R.id.passwordTextField))
                .perform(typeText("12"), closeSoftKeyboard());

        // gib falsches confirm Passwort ein
        onView(withId(R.id.confirmRegisterPasswordEditText))
                .perform(typeText(PASSWORD_STRING_TO_BE_TYPED), closeSoftKeyboard());

        // klicke auf register
        onView(withId(R.id.registerButton)).perform(click());

        //Prüfe Toast
        onView(withText("passwords don't match")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        //deleteUser
        Assert.assertNotSame(state, DebugState.NOT_LOGGED_IN);
    }

    // registriere Nutzer, logge ihn ein und erstelle einen plan
    @Test
    public void registerThanLogin() {

        onView(withId(R.id.toCreateAccountActivityButton)).perform(click());

        // gib Username ein
        onView(withId(R.id.registerUserNameTextField))
                .perform(typeText(USERNAME_STRING_TO_BE_TYPED), closeSoftKeyboard());

        // gib password ein
        onView(withId(R.id.passwordTextField))
                .perform(typeText(PASSWORD_STRING_TO_BE_TYPED), closeSoftKeyboard());

        // gib confirm password ein
        onView(withId(R.id.confirmRegisterPasswordEditText))
                .perform(typeText(PASSWORD_STRING_TO_BE_TYPED), closeSoftKeyboard());

        // klicke auf register
        onView(withId(R.id.registerButton)).perform(click());

        //klicke auf create plan
        onView(withId(R.id.createPlanButton)).perform(click());

        // klicke auf das plan name feld um plannamen einzugeben
        onView(withHint("plan name")).perform(typeText(PLAN_STRING_TO_BE_TYPED), closeSoftKeyboard());

        // klicke auf OK im Alertdialog
        onView(withId(android.R.id.button1)).perform(click());

        // Überprüfe ob wir in Plananischt gelandet sind
        onView(withId(R.id.planNameTextView)).check(matches(withText(PLAN_STRING_TO_BE_TYPED)));

        //Klicke auf delete User
        onView(withId(R.id.deleteUserButton)).perform(click());

        // klicke auf OK im Alertdialog
        onView(withId(android.R.id.button2)).perform(click());

    }


}
