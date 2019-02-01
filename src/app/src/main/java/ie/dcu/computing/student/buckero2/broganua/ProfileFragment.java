package ie.dcu.computing.student.buckero2.broganua;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.support.constraint.Constraints.TAG;

public class ProfileFragment extends Fragment {

    private TextView userName, userAge, userEmail, userShoeSize;
    private Button userEditButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        userName = (TextView)view.findViewById(R.id.tvProfileName);
        userAge = (TextView)view.findViewById(R.id.tvProfileAge);
        userEmail = (TextView)view.findViewById(R.id.tvProfileEmail);
        userShoeSize = (TextView)view.findViewById(R.id.tvProfileShoeSize);
        userEditButton = (Button)view.findViewById(R.id.buttonProfileUpdate);
        Log.d(TAG, "onCreateView: started.");


        return view;
    }
}
