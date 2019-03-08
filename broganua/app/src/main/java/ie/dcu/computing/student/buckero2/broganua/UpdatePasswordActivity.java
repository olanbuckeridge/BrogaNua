package ie.dcu.computing.student.buckero2.broganua;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class UpdatePasswordActivity extends AppCompatActivity {

    private EditText newPassword;
    private Button savePassword;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

            savePassword = (Button) findViewById(R.id.buttonSave);
            newPassword = (EditText) findViewById(R.id.etNewPassword);

            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();



            savePassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userPasswordNew = newPassword.getText().toString();
                    firebaseUser.updatePassword(userPasswordNew).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(UpdatePasswordActivity.this, "Password Updated.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else {
                                Toast.makeText(UpdatePasswordActivity.this, "Password Update Error.", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            });
    }
}
