package ie.dcu.computing.student.buckero2.broganua;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v4.app.FragmentActivity;

public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load settings fragment
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new MainSettingsFragment()).commit();

    }

    public static class MainSettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
