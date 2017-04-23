package com.sih.pmkvy.about_pmkvy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sih.pmkvy.R;
import com.sih.pmkvy.browse_course.browse_course;
import com.sih.pmkvy.find_centre.traning_centre;
import com.sih.pmkvy.settings.settings;

/**
 * Created by ASUS on 3/27/2017.
 */

public class about_pmkvy extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_pmkvy);
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
}
