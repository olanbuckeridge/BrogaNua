package ie.dcu.computing.student.buckero2.broganua;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import ie.dcu.computing.student.buckero2.broganua.Authentication.RegistrationActvity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class RegistrationTest {

    @Rule
    public final ActivityTestRule<RegistrationActvity> main = new ActivityTestRule<>(RegistrationActvity.class);

    @Test
    public void textView(){
        onView(withId(R.id.tvRegistered)).check( matches(isDisplayed()));

    }

    @Test
    public void checkRegistrationActivityPage() {
        onView(withId(R.id.tvRegistered)).check( matches(withText("Already Registered? Login")));
    }

    @Test
    public void btnclickable(){
        onView(withId(R.id.registerButton)).check(matches(isEnabled()));
    }
}
