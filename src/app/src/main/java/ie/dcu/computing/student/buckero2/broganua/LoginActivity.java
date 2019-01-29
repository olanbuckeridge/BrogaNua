package ie.dcu.computing.student.buckero2.broganua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int attempts = 5;
    private TextView userRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Name = (EditText) findViewById(R.id.enterName);
        Password = (EditText) findViewById(R.id.enterPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.loginButton);
        userRegistration = (TextView) findViewById(R.id.tvRegister);

        Info.setText("No. of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString().toLowerCase(), Password.getText().toString());
            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActvity.class));
            }
        });

    }

    private void validate(String userName, String userPassword) {
        if ((userName.equals("admin")) && (userPassword.equals("1234"))){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else {
            attempts--;

            Info.setText("No. of attempts remaining: " + String.valueOf(attempts));

            if (attempts == 0) {
                Login.setEnabled(false);
            }
        }
    }
}
