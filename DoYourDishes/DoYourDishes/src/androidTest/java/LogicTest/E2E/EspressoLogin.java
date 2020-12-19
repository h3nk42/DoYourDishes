/**
 * Testet ob Registrierter User sich einloggen kann
 */
package LogicTest.espressoTest;


import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.control.controllerLogic.DebugState;
import com.view.R;
import com.view.gui.LandingActivity;
import com.view.gui.LoginActivity;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.is;



@RunWith(AndroidJUnit4.class)

@LargeTest
public class EspressoLogin {
    DebugState state;
    public static final String USERNAME_STRING_TO_BE_TYPED = "harun";
    public static final String PASSWORD_STRING_TO_BE_TYPED = "harun1";
    public static final String WRONGUSERNAME_STRING_TO_BE_TYPED = "wrongy";     // please dont register this User
    public static final String WRONGPASSWORD_STRING_TO_BE_TYPED = "wrongy1";    // please dont register this User

    @Rule
    public ActivityScenarioRule<LoginActivity> activityScenarioRule
            = new ActivityScenarioRule<>(LoginActivity.class);

    private View decorView;

    @Before
    public void launchActivity() {
        ActivityScenario.launch(LandingActivity.class);
    }

    @Before
    public void setUp(){
        activityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<LoginActivity>() {
            @Override
            public void perform(LoginActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }


    /**
     * User loggt sich mit gültigen Nutzerdaten ein
     */
    @Test
    public void LoginGutTest() {
        onView(withId(R.id.toLoginButton)).perform(click());

        // gib Username ein
        onView(withId(R.id.userNameEditTextView))
                .perform(typeText(USERNAME_STRING_TO_BE_TYPED), closeSoftKeyboard());

        // gib password ein
        onView(withId(R.id.passwordEditTextView))
                .perform(typeText(PASSWORD_STRING_TO_BE_TYPED), closeSoftKeyboard());

        // klicke auf login
        onView(withId(R.id.toLoginButton)).perform(click());

        // my Welcome Screen
        onView(withId(R.id.welcomeUserNameTextView)).check(matches(withText("Welcome " + USERNAME_STRING_TO_BE_TYPED + "!")));
        //Assert.assertTrue(state==DebugState.LOGGED_IN);

        //TODO: arbeiten an state
    }

    /**
     * Userloggt sich mit falschen Userdaten ein
     */
    @Ignore
    @Test
    public void LoginSchlechtTest1(){
        onView(withId(R.id.toLoginButton)).perform(click());

        // gib Username ein
        onView(withId(R.id.userNameEditTextView))
                .perform(typeText(WRONGUSERNAME_STRING_TO_BE_TYPED), closeSoftKeyboard());

        // gib password ein
        onView(withId(R.id.passwordEditTextView))
                .perform(typeText(WRONGPASSWORD_STRING_TO_BE_TYPED), closeSoftKeyboard());

        // klicke auf login
        onView(withId(R.id.toLoginButton)).perform(click());

        //

        // Toast-Message
        onView(withText("no username/password given")).inRoot(withDecorView(is(decorView))).check(matches(isDisplayed()));
    }

    //TODO: User loggt sich mit keinen Daten ein
}
