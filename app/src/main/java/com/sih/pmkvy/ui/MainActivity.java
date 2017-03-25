package com.sih.pmkvy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sih.pmkvy.course_info.course_info_activity;
import com.sih.pmkvy.R;
import com.sih.pmkvy.find_centre.traning_centre;
import com.sih.pmkvy.signup.signup_activity_student;
import com.sih.pmkvy.login.*;
import  com.sih.pmkvy.homepage.*;
import com.sih.pmkvy.settings.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button signup, centre_list, login_btn, set_btn, course_info, home_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup = (Button) findViewById(R.id.goto_signup);
        set_btn = (Button) findViewById(R.id.settings);
        home_page = (Button) findViewById(R.id.homepage_btn);
        centre_list = (Button) findViewById(R.id.goto_training_centre);
        login_btn = (Button) findViewById(R.id.login_main);
        course_info = (Button) findViewById(R.id.courseinfo);

        login_btn.setOnClickListener(this);
        signup.setOnClickListener(this);
        centre_list.setOnClickListener(this);
        set_btn.setOnClickListener(this);
        home_page.setOnClickListener(this);
        course_info.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent training = new Intent(this, traning_centre.class);
        Intent signup = new Intent(this, signup_activity_student.class);
        Intent login = new Intent(this, login_activity_student.class);
        Intent settings = new Intent(this, settings.class);
        Intent course = new Intent(this, course_info_activity.class);
        Intent homepage=new Intent(this,homepage.class);
        if (R.id.goto_signup == v.getId()) {
            startActivity(signup);

        } else {
            if (R.id.goto_training_centre == v.getId()) {
                startActivity(training);
            } else {
                if (R.id.login_main == v.getId()) {
                    startActivity(login);
                } else if (R.id.settings == v.getId()) {
                    startActivity(settings);
                } else if(R.id.homepage_btn==v.getId()) {
                    startActivity(homepage);
                }else {
                    startActivity(course);
                }
            }
        }

        // calling an activity using <intent-filter> action name
        //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");

        //startActivity(inent);
    }
}
