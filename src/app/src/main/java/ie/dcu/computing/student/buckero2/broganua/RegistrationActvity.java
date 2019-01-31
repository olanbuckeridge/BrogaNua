package ie.dcu.computing.student.buckero2.broganua;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActvity extends AppCompatActivity {

    private EditText userName;
    private EditText userEmail;
    private EditText userPassword;
    private Button registerButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_actvity);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    //Upload data to the database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                sendEmailVerification();
                            }
                            else {
                                Toast.makeText(RegistrationActvity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActvity.this, LoginActivity.class));
            }
        });
    }

    private void setupUIViews () {
        userName = (EditText) findViewById(R.id.etUserName);
        userEmail = (EditText) findViewById(R.id.etUserEmail);
        userPassword = (EditText) findViewById(R.id.etPassword);
        userLogin = (TextView) findViewById(R.id.tvRegistered);
        registerButton = (Button) findViewById(R.id.registerButton);


    }

    private Boolean validate() {
        Boolean result = false;

        String name = userName.getText().toString();
        String password = userPassword.getText().toString();
        String email = userEmail.getText().toString();

        if (name.isEmpty() || password.isEmpty() || email.isEmpty()) {
            Toast.makeText(RegistrationActvity.this, "Please fill in all details", Toast.LENGTH_SHORT).show();
        }
        else {
            result = true;
        }

        return result;
    }

    private void sendEmailVerification () {
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegistrationActvity.this, "Successfully registered! Please verify your email, verification email has been sent.", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        startActivity(new Intent(RegistrationActvity.this, LoginActivity.class));
                    }
                    else {
                        Toast.makeText(RegistrationActvity.this, "Verification email hasn't been sent", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
