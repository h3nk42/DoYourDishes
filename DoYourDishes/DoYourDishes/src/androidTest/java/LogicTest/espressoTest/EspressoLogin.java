package LogicTest.espressoTest;


import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.control.logic.HomeController;
import com.view.R;
import com.view.gui.HomeActivity;
import com.view.gui.LandingActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)

@LargeTest
public class EspressoLogin {

    public static final String USERNAME_STRING_TO_BE_TYPED = "harun";
    public static final String PASSWORD_STRING_TO_BE_TYPED = "harun1";

    @Before
    public void launchActivity() {
        ActivityScenario.launch(LandingActivity.class);
    }

    @Test
    public void LoginGutTest() {
        onView(withId(R.id.toLoginButton)).perform(click());

        // gib Username ein
        onView(withId(R.id.userNameEditTextView))
                .perform(typeText(USERNAME_STRING_TO_BE_TYPED), closeSoftKeyboard());

        // gib password ein
        onView(withId(R.id.passwordEditTextView))
                .perform(typeText(PASSWORD_STRING_TO_BE_TYPED), closeSoftKeyboard());

        // klicke auf register
        onView(withId(R.id.toLoginButton)).perform(click());

        // bin ich hier gelandet
        //onView(allOf(withId(R.id.welcomeUserNameTextView), withText("Hello!"))).check(matches(isDisplayed()));

        onView(withId(R.id.welcomeUserNameTextView)).check(matches(withText("Welcome " + USERNAME_STRING_TO_BE_TYPED + "!")));
    }
}
