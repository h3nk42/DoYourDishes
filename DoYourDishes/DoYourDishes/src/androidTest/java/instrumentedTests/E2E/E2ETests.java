package instrumentedTests.E2E;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import com.view.R;
import com.view.gui.LandingActivity;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class E2ETests {

    @Before
    public void launchActivity() {
        ActivityScenario.launch(LandingActivity.class);
    }

    @Test
    public void BiggestTest(){

        /////////////////////////////////////////////////////////////////////////////
        //                          create User Procedures                        ///
        //////////////////////////////////////////////////////////////////////////////

        onView(withId(R.id.toCreateAccountActivityButton)).perform(click());

        // gib Username ein
        onView(withId(R.id.registerUserNameTextField))
                .perform(typeText("harun102"), closeSoftKeyboard());

        // gib password ein
        onView(withId(R.id.passwordTextField))
                .perform(typeText("iHeartAndroid"), closeSoftKeyboard());

        // gib confirm password ein
        onView(withId(R.id.confirmRegisterPasswordEditText))
                .perform(typeText("iHeartAndroid"), closeSoftKeyboard());

        // klicke auf register
        onView(withId(R.id.registerButton)).perform(click());


        onView(withId(R.id.textView4)).check(matches(withText("it's nice to have you back")));


    }


}
