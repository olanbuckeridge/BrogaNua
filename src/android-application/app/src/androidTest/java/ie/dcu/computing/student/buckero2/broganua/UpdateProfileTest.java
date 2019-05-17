package ie.dcu.computing.student.buckero2.broganua;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class UpdateProfileTest {
    @Rule
    public final ActivityTestRule<UpdateProfileActivity> main = new ActivityTestRule<>(UpdateProfileActivity.class);


    @Test
    public void checkUpdatePasswordActivityPage() {
        onView(withId(R.id.buttonUpdate)).check( matches(withText("Update")));
    }

    @Test
    public void btnclickable(){
        onView(withId(R.id.buttonUpdate)).check(matches(isEnabled()));
    }
}
