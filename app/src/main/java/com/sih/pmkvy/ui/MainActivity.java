package com.sih.pmkvy.ui;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sih.pmkvy.R;
import com.sih.pmkvy.find_centre.traning_centre;
import com.sih.pmkvy.signup.signup_activity_student;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button signup,centre_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup=(Button)findViewById(R.id.goto_signup);
        centre_list=(Button)findViewById(R.id.goto_training_centre);

        signup.setOnClickListener(this);
        centre_list.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent training = new Intent(this, traning_centre.class);
        Intent signup = new Intent(this, signup_activity_student.class);

        if(R.id.goto_signup==v.getId())
        {
            startActivity(signup);

        }
        else
        {
            startActivity(training);
        }

        // calling an activity using <intent-filter> action name
        //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");

        //startActivity(inent);
    }
}
