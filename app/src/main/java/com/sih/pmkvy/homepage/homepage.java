package com.sih.pmkvy.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sih.pmkvy.R;
import com.sih.pmkvy.find_centre.traning_centre;

/**
 * Created by hp on 21-03-2017.
 */

public class homepage extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        Button b1,b2,b3,b4,b5;
        b1=(Button)findViewById(R.id.b1);
        b1.setOnClickListener(this);
        b2=(Button)findViewById(R.id.b2);
        b2.setOnClickListener(this);
        b3=(Button)findViewById(R.id.b3);
        b3.setOnClickListener(this);
        b4=(Button)findViewById(R.id.b4);
        b4.setOnClickListener(this);
        b5=(Button)findViewById(R.id.b5);
        b5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent b2 = new Intent(this, traning_centre.class);
    }
}
