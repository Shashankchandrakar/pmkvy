package com.sih.pmkvy.ui;


import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sih.pmkvy.gps_location.*;
import com.sih.pmkvy.course_info.course_info_activity;
import com.sih.pmkvy.R;
import com.sih.pmkvy.find_centre.traning_centre;
import com.sih.pmkvy.signup.signup_activity_student;
import com.sih.pmkvy.login.*;
import com.sih.pmkvy.homepage.*;
import com.sih.pmkvy.settings.*;
import com.sih.pmkvy.course_provided_center.*;
import com.sih.pmkvy.registration_form.*;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button signup, centre_list, login_btn, set_btn, course_info, home_page, course_provided, register_form, gps, notifiaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        course_provided = (Button) findViewById(R.id.course_btn_main);
        signup = (Button) findViewById(R.id.goto_signup);
        set_btn = (Button) findViewById(R.id.settings);
        home_page = (Button) findViewById(R.id.homepage_btn);
        centre_list = (Button) findViewById(R.id.goto_training_centre);
        login_btn = (Button) findViewById(R.id.login_main);
        course_info = (Button) findViewById(R.id.courseinfo);
        register_form = (Button) findViewById(R.id.registration_form);
        gps = (Button) findViewById(R.id.gps);
        notifiaction = (Button) findViewById(R.id.notification);

        notifiaction.setOnClickListener(this);
        gps.setOnClickListener(this);
        register_form.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        course_provided.setOnClickListener(this);
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
        Intent homepage = new Intent(this, homepage.class);
        Intent register = new Intent(this, registration_form.class);
        Intent gps = new Intent(this, gps_location.class);
        Intent course_provided = new Intent(this, course_provide_center_activity.class);
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
                } else if (R.id.homepage_btn == v.getId()) {
                    startActivity(homepage);
                } else if (R.id.course_btn_main == v.getId()) {
                    startActivity(course_provided);
                } else if (R.id.registration_form == v.getId()) {
                    startActivity(register);
                } else if (R.id.gps == v.getId()) {
                    startActivity(gps);

                } else if (v.getId() == R.id.notification) {
                    startnotification();
                } else {
                    startActivity(course);
                }
            }
        }

        // calling an activity using <intent-filter> action name
        //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");

        //startActivity(inent);
    }

    private void startnotification() {

    }

}
