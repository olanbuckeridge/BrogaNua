package ie.dcu.computing.student.buckero2.broganua;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import ie.dcu.computing.student.buckero2.broganua.Authentication.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class LoginTest {

    @Rule
    public final ActivityTestRule<LoginActivity> main = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void textView(){
        onView(withId(R.id.tvPassReset)).check( matches(isDisplayed()));
        onView(withId(R.id.tvInfo)).check( matches(isDisplayed()));

    }

    @Test
    public void checkLoginActivityPage() {
        onView(withId(R.id.tvPassReset)).check( matches(withText("Forgot Password?")));
        onView(withId(R.id.tvInfo)).check( matches(withText("No. of attempts remaining: 5")));
    }

    @Test
    public void btnclickable(){
        onView(withId(R.id.loginButton)).check(matches(isEnabled()));
    }
}
