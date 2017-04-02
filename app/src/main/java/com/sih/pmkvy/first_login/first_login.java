package com.sih.pmkvy.first_login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sih.pmkvy.R;
import com.sih.pmkvy.feedback.feedback;
import com.sih.pmkvy.login.*;
/**
 * Created by Nidhi Singh on 4/1/2017.
 */

public class first_login extends AppCompatActivity implements View.OnClickListener {
    Button btn_student_login, btn_employer_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_login);
        //Toast.makeText(this,"REALLY",Toast.LENGTH_SHORT).show();
        btn_student_login = (Button)findViewById(R.id.btn_student_login_first);
        btn_employer_login = (Button)findViewById(R.id.btn_employer_login_first);

        btn_student_login.setOnClickListener(this);
        btn_employer_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_student_login_first) {
            Intent login = new Intent(v.getContext(),login_activity_student.class);
            startActivity(login);
        }
        else{
            startActivity(new Intent(v.getContext(),login_activity_student.class));
        }
    }
}
