package com.sih.pmkvy.register_course;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sih.pmkvy.R;
import com.sih.pmkvy.registration_form.*;

/**
 * Created by ASUS on 4/2/2017.
 */

public class regsiter_home extends AppCompatActivity implements View.OnClickListener {
    Button aadhar,form;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_register);

        aadhar=(Button)findViewById(R.id.register_aadhar);
        form=(Button)findViewById(R.id.register_form);

        aadhar.setOnClickListener(this);
        form.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(R.id.register_aadhar==v.getId()){
            startActivity(new Intent(v.getContext(),aadhar_register.class));
        }
        else{
            startActivity(new Intent(v.getContext(),registration_form.class));
        }

    }
}
