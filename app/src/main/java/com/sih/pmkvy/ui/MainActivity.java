package com.sih.pmkvy.ui;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.sih.pmkvy.about_pmkvy.about_pmkvy;
import com.sih.pmkvy.student_dashboard.*;
import com.sih.pmkvy.navigation_bar.*;
import com.sih.pmkvy.*;
import com.sih.pmkvy.browse_course.*;
import com.sih.pmkvy.gps_location.*;
import com.sih.pmkvy.course_info.course_info_activity;
import com.sih.pmkvy.R;
import com.sih.pmkvy.find_centre.traning_centre;
import com.sih.pmkvy.signup.signup_activity_student;
import com.sih.pmkvy.login.*;
import com.sih.pmkvy.homepage.*;
import com.sih.pmkvy.feedback.*;
import com.sih.pmkvy.settings.*;
import com.sih.pmkvy.course_provided_center.*;
import com.sih.pmkvy.registration_form.*;
import com.sih.pmkvy.language_choice.*;
import com.sih.pmkvy.first_login.*;

import java.util.Locale;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button signup, nav, centre_list, login_btn, set_btn, hindi, english, first_login,
            browse_course, course_info, feedback, home_page, course_provided, register_form, gps, notifiaction, dashboard, session;
    Locale mLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, language_choice.class));
        dashboard = (Button) findViewById(R.id.dashboard);
        hindi = (Button) findViewById(R.id.hindi);
        english = (Button) findViewById(R.id.english);
        browse_course = (Button) findViewById(R.id.browse_course_homepage);
        feedback = (Button) findViewById(R.id.feedback_homepage);
        nav = (Button) findViewById(R.id.nav);
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
        session = (Button) findViewById(R.id.session);
        first_login = (Button) findViewById(R.id.language_selector);

        notifiaction.setOnClickListener(this);
        hindi.setOnClickListener(this);
        english.setOnClickListener(this);
        first_login.setOnClickListener(this);
        dashboard.setOnClickListener(this);
        nav.setOnClickListener(this);
        feedback.setOnClickListener(this);
        gps.setOnClickListener(this);
        register_form.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        course_provided.setOnClickListener(this);
        browse_course.setOnClickListener(this);
        signup.setOnClickListener(this);
        centre_list.setOnClickListener(this);
        set_btn.setOnClickListener(this);
        home_page.setOnClickListener(this);
        course_info.setOnClickListener(this);
        session.setOnClickListener(this);


    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.browse_course_menu:
                startActivity(new Intent(this, browse_course.class));
                return true;
            case R.id.find_training_center_menu:
                startActivity(new Intent(this, traning_centre.class));
                return true;

            case R.id.about_menu:
                startActivity(new Intent(this, about_pmkvy.class));
                return true;
            case R.id.settings_menu:
                startActivity(new Intent(this, settings.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        //respond to menu item selection

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
        Intent gps = new Intent(this, employer_training_list.class);
        Intent course_provided = new Intent(this, course_provide_center_activity.class);
        Intent session = new Intent(this, getdata.class);
        Intent feedback = new Intent(this, feedback.class);
        Intent navi = new Intent(this, navigation_bar.class);
        Intent browse_course = new Intent(this, browse_course.class);
        Intent lang = new Intent(this, language_choice.class);
        Intent dashboard = new Intent(this, dashboardactivity.class);

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
                } else if (v.getId() == R.id.session) {
                    startActivity(session);
                } else if (v.getId() == R.id.feedback_homepage) {
                    startActivity(feedback);
                } else if (R.id.nav == v.getId()) {
                    startActivity(navi);
                } else if (R.id.dashboard == v.getId()) {
                    startActivity(dashboard);
                } else if (v.getId() == R.id.browse_course_homepage) {
                    startActivity(browse_course);
                } else if (R.id.english == v.getId()) {
                    mLocale = new Locale("eng");
                    Locale.setDefault(mLocale);
                    Configuration config2 = new Configuration();
                    config2.locale = mLocale;
                    getBaseContext().getResources().updateConfiguration(config2,
                            getBaseContext().getResources().getDisplayMetrics());
                    this.setContentView(R.layout.activity_main);
                    this.recreate();
                } else if (R.id.hindi == v.getId()) {
                    mLocale = new Locale("hi");
                    Locale.setDefault(mLocale);
                    Configuration config = new Configuration();
                    config.locale = mLocale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());
                    MainActivity.this.setContentView(R.layout.activity_main);
                    this.recreate();

                } else if (R.id.language_selector == v.getId()) {
                    startActivity(lang);
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
