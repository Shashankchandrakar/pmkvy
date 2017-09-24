package com.sih.pmkvy.register_course;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sih.pmkvy.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by ASUS on 4/2/2017.
 */

public class aadhar_register extends AppCompatActivity implements View.OnClickListener {
    EditText aadhar_no,mobile_no;
    Button verfiy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aadhar_register);

        aadhar_no=(EditText) findViewById(R.id.aadhar_no);
        mobile_no=(EditText) findViewById(R.id.mobile_no);
        verfiy=(Button)findViewById(R.id.verify_register);

        verfiy.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        new get_request(this,aadhar_no.getText().toString()).execute();

    }
}

class get_request extends AsyncTask<String, Void, String> {
    Context context;
    String aadhar;
    RecyclerView rv;
    String center_id, course_id, job_role_name, job_sector, course_name;
    JSONObject json;
    Boolean flag;
    TextView course_name_t, course_id_t, skill_sector_t, job_role_t;


    public get_request(Context context, String aadhar)
    {
        this.context=context;
        this.aadhar=aadhar;

    }


    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_SHORT).show();

    }

    @Override

    protected String doInBackground(String... params) {

        try {
            String link = context.getResources().getString(R.string.link) + "/api/sendotp/";

            URL url = new URL(link);
            URLConnection con = url.openConnection();

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            json = new JSONObject();
            JSONObject add = new JSONObject();

            //add.put("job_role_name", job_role_name);
            add.put("ad_aadhar_no", aadhar);


            wr.write(add.toString());
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                Log.d("LINE : ", line);
                if (line.equals("true")) {
                    //TODO: 3/20/2017 add response checking from server format is in jason
                    flag = true;
                } else
                    flag = false;

                sb.append(line);
            }
            return sb.toString();


        } catch (Exception e) {
            Log.d("ERROR", e.getMessage());
            return "Exception: " + e.getMessage();
        }

    }
}
