package instrumentedTests.E2E;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import com.view.R;
import com.view.gui.LandingActivity;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import instrumentedTests.ShortcutEngine.RandomGenerator;
import instrumentedTests.ShortcutEngine.RandomGeneratorImpl;
import instrumentedTests.ShortcutEngine.ToastMatcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class E2ETests {

    ////////////////////////////////////////////////////////////////////////////////
    //                         Presentation required attr.                      ///
    //////////////////////////////////////////////////////////////////////////////
    RandomGenerator rando = new RandomGeneratorImpl();

    public final String USERNAME = rando.generateStringAndReturn() + "x";
    public final String PASSWORD = rando.generateStringAndReturn() + "passx";

    public final String USERNAME1 = rando.generateStringAndReturn() + "1";
    public final String PASSWORD1 = rando.generateStringAndReturn() + "pass1";
    public final String USERNAME2 = rando.generateStringAndReturn() + "2";
    public final String PASSWORD2 = rando.generateStringAndReturn() + "pass2";
    public final String PLAN = "Our Plan";
    public final String TASK = "Our Task";

    public final int POINTS = rando.generateIntAndReturn();

    @Before
    public void launchActivity() {
        ActivityScenario.launch(LandingActivity.class);
    }

    //Hilfsmethode für RecyclerView, Cardview -operation
    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a button within our Card View with specified id";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }


    @Test
    public void BiggestTest1() {

        /////////////////////////////////////////////////////////////////////////////
        //                          create User Procedures                        ///
        //////////////////////////////////////////////////////////////////////////////

        onView(withId(R.id.toCreateAccountActivityButton)).perform(click());

        // gib Username ein
        onView(withId(R.id.registerUserNameTextField))
                .perform(typeText(USERNAME), closeSoftKeyboard());

        // gib password ein
        onView(withId(R.id.passwordTextField))
                .perform(typeText(PASSWORD), closeSoftKeyboard());

        // gib confirm password ein
        onView(withId(R.id.confirmRegisterPasswordEditText))
                .perform(typeText(PASSWORD), closeSoftKeyboard());

        // klicke auf register
        onView(withId(R.id.registerButton)).perform(click());


        onView(withText("you're logged in!")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

    }

    @Test
    public void E2ETestforPresentation() {

        ///////////////////////////////////////////////////////////////////////////////
        //                          Register User1                                  ///
        //////////////////////////////////////////////////////////////////////////////

        onView(withId(R.id.toCreateAccountActivityButton)).perform(click());

        // gib Username ein
        onView(withId(R.id.registerUserNameTextField))
                .perform(typeText(USERNAME1), closeSoftKeyboard());

        // gib password ein
        onView(withId(R.id.passwordTextField))
                .perform(typeText(PASSWORD1), closeSoftKeyboard());

        // gib confirm password ein
        onView(withId(R.id.confirmRegisterPasswordEditText))
                .perform(typeText(PASSWORD1), closeSoftKeyboard());

        // klicke auf register
        onView(withId(R.id.registerButton)).check(matches(withText("Register"))).perform(click());

        //prüfe Toast
        onView(withText("you're logged in!")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        ////////////////////////////////////////////////////////////////////////////////
        //                          LogOut User1                                    ///
        //////////////////////////////////////////////////////////////////////////////

        //klicke auf HardwareButton um dich auszuloggen
        onView(isRoot()).perform(pressBack());

        ////////////////////////////////////////////////////////////////////////////////
        //                          Register User2                                  ///
        //////////////////////////////////////////////////////////////////////////////

        onView(withId(R.id.toCreateAccountActivityButton)).perform(click());

        // gib Username ein
        onView(withId(R.id.registerUserNameTextField))
                .perform(typeText(USERNAME2), closeSoftKeyboard());

        // gib password ein
        onView(withId(R.id.passwordTextField))
                .perform(typeText(PASSWORD2), closeSoftKeyboard());

        // gib confirm password ein
        onView(withId(R.id.confirmRegisterPasswordEditText))
                .perform(typeText(PASSWORD2), closeSoftKeyboard());

        // klicke auf register
        onView(withId(R.id.registerButton)).check(matches(withText("Register"))).perform(click());

        //Prüfe Toast
        onView(withText("you're logged in!")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        ////////////////////////////////////////////////////////////////////////////////
        //                          User2 Create a Plan                             ///
        //////////////////////////////////////////////////////////////////////////////

        //klicke auf createPlan
        onView(withId(R.id.createPlanButton)).perform(click());

        //gibt plannamen ein
        onView(withHint("plan name")).perform(typeText(PLAN), closeSoftKeyboard());

        // klicke auf OK im Alertdialog
        onView(withId(android.R.id.button1)).perform(click());

        //prüfe ob dieser Plan erstellt
        onView(withId(R.id.planNameTextView)).check(matches(withText(PLAN)));

        ////////////////////////////////////////////////////////////////////////////////
        //                          User2 adds User1 to Plan                        ///
        //////////////////////////////////////////////////////////////////////////////

        //Klicke auf Plan
        onView(withId(R.id.goToPlanViewButton)).perform(click());

        // Klicke im TabLayout auf Reiter #Users
        onView(ViewMatchers.withText("users")).perform(ViewActions.click());

        //Klicke auf #ADD USER
        onView(withId(R.id.button)).perform(click());

        //gib Username ein
        onView(withHint("taskName")).perform(typeText(USERNAME1), closeSoftKeyboard());

        //Klicke auf OK im AlertDialog
        onView(withText("OK")).perform(click());

        //prüfe ob  User im Plan
        onView(withId(R.id.usersFragmentRecyclerView)).check(matches(hasDescendant(withText(USERNAME1))));

        ////////////////////////////////////////////////////////////////////////////////
        //                          2xHarwareButton                                 ///
        //////////////////////////////////////////////////////////////////////////////
        onView(isRoot()).perform(pressBack());
        onView(isRoot()).perform(pressBack());

        ////////////////////////////////////////////////////////////////////////////////
        //                          Login User1                                     ///
        //////////////////////////////////////////////////////////////////////////////

        //klicke auf Login
        onView(withId(R.id.toLoginButton)).perform(click());

        // gib Username ein
        onView(withId(R.id.userNameEditTextView))
                .perform(typeText(USERNAME1), closeSoftKeyboard());

        // gib password ein
        onView(withId(R.id.passwordEditTextView))
                .perform(typeText(PASSWORD1), closeSoftKeyboard());

        // klicke auf login
        onView(withId(R.id.toLoginButton)).check(matches(withText("Login"))).perform(click());

        //Prüfe Toast
        onView(withText("you're logged in!")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        ////////////////////////////////////////////////////////////////////////////////
        //                          User1 add Task                                  ///
        //////////////////////////////////////////////////////////////////////////////

        //Klicke auf Plan
        onView(withId(R.id.goToPlanViewButton)).perform(click());

        //Klicke auf ADD TASK
        onView(withId(R.id.addTaskButton)).perform(click());

        //gib Tasknamen ein
        onView(withHint("Taskname")).perform(typeText(TASK), closeSoftKeyboard());

        //gib Punktzahl die dieser Task wert sein soll an
        onView(withHint("points worth")).perform(typeText(String.valueOf(POINTS)), closeSoftKeyboard());

        //Klicke auf ADD TASK
        onView(withText("Create")).perform(click());

        //prüfe ob TASK im Plan
        onView(withId(R.id.tasksFragmentRecyclerView)).check(matches(hasDescendant(withText(TASK))));

        ////////////////////////////////////////////////////////////////////////////////
        //                          2xHarwareButton                                 ///
        //////////////////////////////////////////////////////////////////////////////
        onView(isRoot()).perform(pressBack());
        onView(isRoot()).perform(pressBack());

        ////////////////////////////////////////////////////////////////////////////////
        //                          Login User2                                     ///
        //////////////////////////////////////////////////////////////////////////////

        //klicke auf Login
        onView(withId(R.id.toLoginButton)).perform(click());

        // gib Username ein
        onView(withId(R.id.userNameEditTextView))
                .perform(typeText(USERNAME2), closeSoftKeyboard());

        // gib password ein
        onView(withId(R.id.passwordEditTextView))
                .perform(typeText(PASSWORD2), closeSoftKeyboard());


        // klicke auf login
        onView(withId(R.id.toLoginButton)).check(matches(withText("Login"))).perform(click());

        //Prüfe Toast
        onView(withText("you're logged in!")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        ////////////////////////////////////////////////////////////////////////////////
        //                          mark TASK as DONE                                ///
        //////////////////////////////////////////////////////////////////////////////

        //klicke auf goToPlanViewButton
        onView(withId(R.id.goToPlanViewButton)).perform(click());

        //Klicke auf den ausgewählten Task um ihn als erledigt zu markieren
        onView(withId(R.id.tasksFragmentRecyclerView))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(TASK)), clickChildViewWithId(R.id.taskDoneButton)));

        ////////////////////////////////////////////////////////////////////////////////
        //                          delete USER1 of off Plan                        ///
        //////////////////////////////////////////////////////////////////////////////

        // Klicke im TabLayout auf Reiter #Users
        onView(ViewMatchers.withText("users")).perform(ViewActions.click());

        ////////////////////////////////////////////////////////////////////////////////
        //                          1xHarwareButton                                 ///
        //////////////////////////////////////////////////////////////////////////////
        onView(isRoot()).perform(pressBack());

        ////////////////////////////////////////////////////////////////////////////////
        //                          delete Plan                                      //
        //                          delete User1                                   //
        /////////////////////////////////////////////////////////////////////////////

        //klicke auf delete Plan Button
        onView(withId(R.id.deletePlanButton)).perform(click());

        //Klicke im Alertdialog auf OK
        onView(withId(android.R.id.button1)).perform(click());

        //Prüfe Toast
        onView(withText("deletePlanSuccess")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        //Klicke auf delete User
        onView(withId(R.id.deleteUserButton)).perform(click());

        // klicke auf OK im Alertdialog
        onView(withId(android.R.id.button2)).perform(click());

        ////////////////////////////////////////////////////////////////////////////////
        //                          Start App + Login User1                         ///
        //////////////////////////////////////////////////////////////////////////////

        launchActivity();

        //klicke auf Login
        onView(withId(R.id.toLoginButton)).perform(click());

        // gib Username ein
        onView(withId(R.id.userNameEditTextView))
                .perform(typeText(USERNAME2), closeSoftKeyboard());

        // gib password ein
        onView(withId(R.id.passwordEditTextView))
                .perform(typeText(PASSWORD2), closeSoftKeyboard());

        // klicke auf login
        onView(withId(R.id.toLoginButton)).perform(click());

        ////////////////////////////////////////////////////////////////////////////////
        //                          lösche Nutzer                                   //
        /////////////////////////////////////////////////////////////////////////////

        //Klicke auf delete User
        onView(withId(R.id.deleteUserButton)).perform(click());

        // klicke auf OK im Alertdialog
        onView(withId(android.R.id.button2)).perform(click());
    }


}
