package LogicTest.espressoTest;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.view.R;
import com.view.gui.HomeActivity;
import com.view.gui.LandingActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTestTest {

    public static final String USERNAME_STRING_TO_BE_TYPED = "haruno";
    public static final String PASSWORD_STRING_TO_BE_TYPED = "haruno1";
    public static final String PLAN_STRING_TO_BE_TYPED = "EspressoPlan1";

/*    // das erste was der Test machen soll, ei jedem restart (hier: bilde Homeactivity [Toplevel Activity])
    @Rule
    public ActivityScenarioRule<HomeActivity> activityScenarioRule
            = new ActivityScenarioRule<>(HomeActivity.class);*/

    @Before
    public void launchActivity() {
        ActivityScenario.launch(LandingActivity.class);
    }


    // registriere Nutzer und logge ihn ein
    @Test
    public void registerThanLogin(){

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
        onView(withText("plan name")).perform(typeText(PLAN_STRING_TO_BE_TYPED),closeSoftKeyboard());

        // klicke auf OK im Alertdialog
        onView(withId(android.R.id.button1)).perform(click());

        // Überprüfe ob wir in Plananischt gelandet sind
        onView(withId(R.id.planNameTextView)).check(matches(withText(PLAN_STRING_TO_BE_TYPED)));

    }





}
