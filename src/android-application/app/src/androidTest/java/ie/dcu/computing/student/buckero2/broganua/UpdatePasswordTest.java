package ie.dcu.computing.student.buckero2.broganua;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class UpdatePasswordTest {
    @Rule
    public final ActivityTestRule<UpdatePasswordActivity> main = new ActivityTestRule<>(UpdatePasswordActivity.class);


    @Test
    public void checkUpdatePasswordActivityPage() {
        onView(withId(R.id.buttonSave)).check( matches(withText("Save")));
    }

    @Test
    public void btnclickable(){
        onView(withId(R.id.buttonSave)).check(matches(isEnabled()));
    }
}
