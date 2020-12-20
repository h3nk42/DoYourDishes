package instrumentedTests.ShortcutEngine;

import com.view.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class cRudEngine implements IcRUDShortcut {


    /////////////////////////////////////////////////////////////////////////////
    //                          create User Procedures                        ///
    //////////////////////////////////////////////////////////////////////////////
    @Override
    public void createUser(String username, String password) {
        onView(withId(R.id.toCreateAccountActivityButton)).perform(click());

        // gib Username ein
        onView(withId(R.id.registerUserNameTextField))
                .perform(typeText(username), closeSoftKeyboard());

        // gib password ein
        onView(withId(R.id.passwordTextField))
                .perform(typeText(password), closeSoftKeyboard());

        // gib confirm password ein
        onView(withId(R.id.confirmRegisterPasswordEditText))
                .perform(typeText(password), closeSoftKeyboard());

        // klicke auf register
        onView(withId(R.id.registerButton)).perform(click());
    }

    /////////////////////////////////////////////////////////////////////////////
    //                          Login Procedures                               ///
    //////////////////////////////////////////////////////////////////////////////

    @Override
    public void login(String username, String password) {

        //klicke auf Login
        onView(withId(R.id.toLoginButton)).perform(click());

        // gib Username ein
        onView(withId(R.id.userNameEditTextView))
                .perform(typeText(username), closeSoftKeyboard());

        // gib password ein
        onView(withId(R.id.passwordEditTextView))
                .perform(typeText(password), closeSoftKeyboard());

        // klicke auf login
        onView(withId(R.id.toLoginButton)).perform(click());
    }

    /////////////////////////////////////////////////////////////////////////////
    //                          create Plan Procedures                        ///
    //////////////////////////////////////////////////////////////////////////////
    @Override
    public void createPlan(String planname) {
        //klicke auf createPlan
        onView(withId(R.id.createPlanButton)).perform(click());

        //gibt plannamen ein
        onView(withHint("plan name")).perform(typeText(planname), closeSoftKeyboard());

        // klicke auf OK im Alertdialog
        onView(withId(android.R.id.button1)).perform(click());

    }


    /////////////////////////////////////////////////////////////////////////////
    //                          delete Plan Procedures                        ///
    //////////////////////////////////////////////////////////////////////////////
    @Override
    public void deletePlan() {
        //klicke auf delete Plan Button
        onView(withId(R.id.deletePlanButton)).perform(click());

        //Klicke im Alertdialog auf OK
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Override
    public void deletUser() {
        //Klicke auf delete User
        onView(withId(R.id.deleteUserButton)).perform(click());

        // klicke auf OK im Alertdialog
        onView(withId(android.R.id.button2)).perform(click());
    }

    @Override
    public void addTask(String taskname, int points) {
        //Klicke auf Plan
        onView(withId(R.id.goToPlanViewButton)).perform(click());

        //Klicke auf ADD TASK
        onView(withId(R.id.addTaskButton)).perform(click());

        //gib Tasknamen ein
        onView(withHint("Taskname")).perform(typeText(taskname), closeSoftKeyboard());

        //gib Punktzahl die dieser Task wert sein soll an
        onView(withHint("points worth")).perform(typeText(String.valueOf(points)), closeSoftKeyboard());

        //Klicke auf ADD TASK
        onView(withText("Create")).perform(click());

        // drücke auf hardwareButton zurück
        onView(withId(R.id.addTaskButton)).perform(pressBack());
    }
}
