package com.sih.pmkvy.language_choice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sih.pmkvy.first_login.first_login;

import com.sih.pmkvy.R;

import java.util.Locale;

/**
 * Created by ASUS on 4/2/2017.
 */

public class language_choice extends AppCompatActivity implements View.OnClickListener {
    Button hindi, english;
    Locale mLocale;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_selector);
        hindi = (Button) findViewById(R.id.hindi_select);
        english = (Button) findViewById(R.id.english_select);

        hindi.setOnClickListener(this);
        english.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        SharedPreferences sharedPreferences;
        sharedPreferences = v.getContext().getSharedPreferences("PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (v.getId()) {
            case R.id.english_select:
                mLocale = new Locale("eng");
                Locale.setDefault(mLocale);
                Configuration config2 = new Configuration();
                config2.locale = mLocale;
                getBaseContext().getResources().updateConfiguration(config2,
                        getBaseContext().getResources().getDisplayMetrics());

                this.recreate();
                new Intent(v.getContext(), first_login.class);
                editor.putString("lang", "eng");
                editor.apply();
                editor.commit();

                break;
            case R.id.hindi_select:
                mLocale = new Locale("hi");
                Locale.setDefault(mLocale);
                Configuration config = new Configuration();
                config.locale = mLocale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());

                this.recreate();
                new Intent(v.getContext(), first_login.class);
                editor.putString("lang", "hi");
                editor.apply();
                editor.commit();
                break;


        }
        //Toast.makeText(this, "LLLOL", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(v.getContext(), first_login.class));

    }
}
