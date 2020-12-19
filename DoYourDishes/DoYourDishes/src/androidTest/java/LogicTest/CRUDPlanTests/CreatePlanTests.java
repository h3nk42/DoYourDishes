package LogicTest.CRUDPlanTests;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import com.view.R;
import com.view.gui.LandingActivity;

import org.junit.Before;
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
@SmallTest
public class CreatePlanTests {

    RandomStringGeneratorForLogin rando = new RandomStringGeneratorForLogin();

    public final String USERNAME = "harun";
    public final String PASSWORD = "harun1";
    public final String PLAN = rando.generateStringAndReturn()+" Plan";

    @Before
    public void launchActivity() {
        ActivityScenario.launch(LandingActivity.class);
    }


     /////////////////////////////////////////////////////////////////////////////
   //                          Login Procedures                               ///
  //////////////////////////////////////////////////////////////////////////////

    @Test
    public void createPlanGutTest1() throws NoMatchingViewException, InterruptedException {

        /////////////////////////////////////////////////////////////////////////////
        //                          Login Procedures                               ///
        //////////////////////////////////////////////////////////////////////////////

        onView(withId(R.id.toLoginButton)).perform(click());

        // gib Username ein
        onView(withId(R.id.userNameEditTextView))
                .perform(typeText(USERNAME), closeSoftKeyboard());

        // gib password ein
        onView(withId(R.id.passwordEditTextView))
                .perform(typeText(PASSWORD), closeSoftKeyboard());

        // klicke auf login
        onView(withId(R.id.toLoginButton)).perform(click());

        //Boolean Condition möglich!



try {

    /////////////////////////////////////////////////////////////////////////////
    //                          delete Procedures                               ///
    //////////////////////////////////////////////////////////////////////////////

    onView(withId(R.id.deletePlanButton)).perform(click());

    onView(withId(android.R.id.button2)).perform(click());


    /////////////////////////////////////////////////////////////////////////////
    //                          create Procedures                               ///
    //////////////////////////////////////////////////////////////////////////////

    onView(withId(R.id.createPlanButton)).perform(click());

    onView(withHint("plan name")).perform(typeText(PLAN),closeSoftKeyboard());

    // klicke auf OK im Alertdialog
    onView(withId(android.R.id.button1)).perform(click());

    // Überprüfe ob wir in Plananischt gelandet sind
    onView(withId(R.id.planNameTextView)).check(matches(withText(PLAN)));


}catch (NoMatchingViewException e){

            /////////////////////////////////////////////////////////////////////////////
            //                          create Procedures                               ///
            //////////////////////////////////////////////////////////////////////////////

            onView(withId(R.id.createPlanButton)).perform(click());

            onView(withHint("plan name")).perform(typeText(PLAN),closeSoftKeyboard());

            // klicke auf OK im Alertdialog
            onView(withId(android.R.id.button1)).perform(click());

            // Überprüfe ob wir in Plananischt gelandet sind
            onView(withId(R.id.planNameTextView)).check(matches(withText(PLAN)));

    /////////////////////////////////////////////////////////////////////////////
    //                          delete Procedures                               ///
    //////////////////////////////////////////////////////////////////////////////

    onView(withId(R.id.deletePlanButton)).perform(click());

    onView(withId(android.R.id.button2)).perform(click());

        }



    }



}
