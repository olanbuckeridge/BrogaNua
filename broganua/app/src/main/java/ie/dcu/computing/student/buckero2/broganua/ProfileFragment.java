package ie.dcu.computing.student.buckero2.broganua;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import static android.support.constraint.Constraints.TAG;

public class ProfileFragment extends Fragment {

    private TextView profileName, profileAge, profileEmail, profileShoeSize;
    private Button profileEditButton, passwordChange;
    private ImageView profilePic;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileName = (TextView)view.findViewById(R.id.etProfileName);
        profileAge = (TextView)view.findViewById(R.id.etProfileAge);
        profileEmail = (TextView)view.findViewById(R.id.etProfileEmail);
        profileShoeSize = (TextView)view.findViewById(R.id.etProfileShoeSize);
        profileEditButton = (Button)view.findViewById(R.id.buttonProfileUpdate);
        profilePic = (ImageView)view.findViewById(R.id.ivProfilePic);
        passwordChange = (Button)view.findViewById(R.id.buttonPasswordChange);
        Log.d(TAG, "onCreateView: started.");

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReferenceFromUrl("https://broganua-59918.firebaseio.com/users").child(firebaseAuth.getUid());

        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(firebaseAuth.getUid()).child("Images/Profile Picture").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(profilePic);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                profileName.setText("Name: " + userProfile.getUserName());
                profileAge.setText("Age: " + userProfile.getUserAge());
                profileEmail.setText("Email: " + userProfile.getUserEmail());
                profileShoeSize.setText("Shoe Size (EU): " + userProfile.getUserShoeSize());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        profileEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), UpdateProfileActivity.class));

            }
        });

        passwordChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), UpdatePasswordActivity.class));

            }
        });




        return view;
    }
}
