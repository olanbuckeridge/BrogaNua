package ie.dcu.computing.student.buckero2.broganua;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class PasswordTest {
    @Rule
    public final ActivityTestRule<PasswordActivity> main = new ActivityTestRule<>(PasswordActivity.class);


    @Test
    public void checkPasswordActivityPage() {
        onView(withId(R.id.resetButton)).check( matches(withText("Reset Password")));
    }

    @Test
    public void btnclickable(){
        onView(withId(R.id.resetButton)).check(matches(isEnabled()));
    }
}
