package instrumentedTests.ShortcutEngine;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import com.view.R;

import org.hamcrest.Matcher;

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

public class CrudEngine implements ICrudShortcut {


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
        onView(withId(R.id.registerButton)).check(matches(withText("Register"))).perform(click());

        // rausgenommen weil test manchmal dadurch in loop geht
        //onView(withText("you're logged in!")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
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

        //prüfe ob dieser Plan erstellt
        onView(withId(R.id.planNameTextView)).check(matches(withText(planname)));

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

        //Prüfe Toast
        onView(withText("deletePlanSuccess")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
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
        //prüfe ob TASK im Plan
        onView(withId(R.id.tasksFragmentRecyclerView)).check(matches(hasDescendant(withText(taskname))));

        // drücke auf hardwareButton zurück
        onView(withId(R.id.addTaskButton)).perform(pressBack());

    }

    @Override
    public void addTask1(String taskname, int points) {
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

        //prüfe ob TASK im Plan
        onView(withId(R.id.tasksFragmentRecyclerView)).check(matches(hasDescendant(withText(taskname))));

    }

    @Override
    public void markAsDone(String taskName) {

        //Klicke auf den ausgewählten Task um ihn als erledigt zu markieren
        onView(withId(R.id.tasksFragmentRecyclerView))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(taskName)), clickChildViewWithId(R.id.taskDoneButton)));

    }

    @Override
    public void markAsDone1(String taskName) {
        //Klicke auf Plan
        onView(withId(R.id.goToPlanViewButton)).perform(click());

        //Klicke auf den ausgewählten Task um ihn als erledigt zu markieren
        onView(withId(R.id.tasksFragmentRecyclerView))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(taskName)), clickChildViewWithId(R.id.taskDoneButton)));

        // drücke auf hardwareButton zurück
        onView(withId(R.id.addTaskButton)).perform(pressBack());
    }


    //lösche ausgewählten Task
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


    @Override
    public void deleteTask(String taskName) {
        //Klicke auf den ausgewählten Task um ihn zu löschen
        onView(withId(R.id.tasksFragmentRecyclerView))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(taskName)), clickChildViewWithId(R.id.deleteTaskButton)));


    }


    @Override
    public void pressHardwareButtonBack() {
        onView(isRoot()).perform(pressBack());
    }


    @Override
    public void addUserToPlan(String username) {
        //Klicke auf Plan
        onView(withId(R.id.goToPlanViewButton)).perform(click());

        // Klicke im TabLayout auf Reiter #Users
        onView(ViewMatchers.withText("users")).perform(ViewActions.click());

        //TODO buttonname ändern
        //Klicke auf #ADD USER
        onView(withId(R.id.button)).perform(click());

        //TODO HintText ändern!
        //gib Username ein
        onView(withHint("taskName")).perform(typeText(username), closeSoftKeyboard());

        //Klicke auf OK im AllertDialog
        onView(withText("OK")).perform(click());

        //prüfe ob  User im Plan
        onView(withId(R.id.usersFragmentRecyclerView)).check(matches(hasDescendant(withText(username))));

        //Hardware back
        onView(isRoot()).perform(pressBack());
    }

    @Override
    public void addUserToPlan1(String username) {
        //TODO buttonname ändern
        //Klicke auf #ADD USER
        onView(withId(R.id.button)).perform(click());

        //TODO HintText ändern!
        //gib Username ein
        onView(withHint("taskName")).perform(typeText(username), closeSoftKeyboard());

        //Klicke auf OK im AllertDialog
        onView(withText("OK")).perform(click());

        //prüfe ob  User im Plan
        onView(withId(R.id.usersFragmentRecyclerView)).check(matches(hasDescendant(withText(username))));
    }


    @Override
    public void clickOnPlan() {
        //klicke auf goToPlanViewButton
        onView(withId(R.id.goToPlanViewButton)).perform(click());
    }

    @Override
    public void clickOnUsersTab() {
        // Klicke im TabLayout auf Reiter #Users
        onView(ViewMatchers.withText("users")).perform(ViewActions.click());
    }

    @Override
    public void removeUser(String username) {
        // lösche User aus dem Plan
        onView(withId(R.id.usersFragmentRecyclerView))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(username)), clickChildViewWithId(R.id.removeUserFromPlanButton)));
    }

    @Override
    public void clickRefresh() {

        //klicke auf goToPlanViewButton
        onView(withId(R.id.refreshDataHomeButton)).perform(click()).check(matches(withText("Refresh Data")));

    }

}
