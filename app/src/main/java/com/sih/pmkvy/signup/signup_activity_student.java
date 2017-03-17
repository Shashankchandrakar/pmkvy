package com.sih.pmkvy.signup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sih.pmkvy.R;

/**
 * Created by test on 2/26/2017.
 */

public class signup_activity_student extends AppCompatActivity implements View.OnClickListener {

    private EditText student_name, student_email, student_password;
    private Button signup_button;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_student);
        student_name = (EditText) findViewById(R.id.student_name_signup);
        student_email = (EditText) findViewById(R.id.student_email_signup);
        student_password = (EditText) findViewById(R.id.studnet_password_signup);
        signup_button = (Button) findViewById(R.id.btn_signup);
        signup_button.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        boolean checkValidData = checkData();
        if(checkValidData)
        {
            //TODO: send data to server to create accout
        }
    }

    private boolean checkData() {
        boolean valid = true;
        if (student_name == null || student_name.getText().equals("") || student_name.length() < 3) {
            student_name.setError("Name Should be grater than 3 Digits");
            valid = false;
        } else {
            student_name.setError(null);
        }
        if (student_email == null || !android.util.Patterns.EMAIL_ADDRESS.matcher(student_email.getText()).matches()) {
            valid = false;
            student_email.setError("Enter Valid Email Address");
        } else {
            student_email.setError(null);
        }

        if (student_password == null || student_password.length() < 4) {
            valid = false;
            student_password.setError("Password Should be Greater Than 4 AlpaNumeric ");
        } else {
            student_password.setError(null);
        }
        return valid;
    }
}






















/*import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.sih.pmkvy.R;

        import butterknife.ButterKnife;
        import butterknife.InjectView;

public class signup_activity_student extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @InjectView(R.id.input_name) EditText _nameText;
    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_signup) Button _signupButton;
    @InjectView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_student);
        ButterKnife.inject(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(signup_activity_student.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
*/
