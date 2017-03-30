package com.sih.pmkvy.feedback;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.sih.pmkvy.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static com.sih.pmkvy.R.id.spinner_course_name;


/**
 * Created by hp on 25-03-2017.
 */

public class feedback extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    EditText _email, _subject, _details;
    Spinner sp_course_name;
    RatingBar rating;
    Button submit;


    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        this.setTitle("Feedback");
        _subject = (EditText) findViewById(R.id.subject_feedback);
        _details = (EditText) findViewById(R.id.details_feedback);
        rating = (RatingBar) findViewById(R.id.rating);
        submit = (Button) findViewById(R.id.submit_feedback);


        submit.setOnClickListener(this);

        sp_course_name = (Spinner) findViewById(spinner_course_name);
        sp_course_name.setOnItemSelectedListener(this);
        //new get_request_subject()
        List<String> categories_course_name = new ArrayList<String>();

        categories_course_name.add("biology");
        categories_course_name.add("physics");
        categories_course_name.add("maths");

        ArrayAdapter<String> adapter_course_name = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories_course_name);
        adapter_course_name.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_course_name.setAdapter(adapter_course_name);

    }

    @Override
    public void onClick(View v) {
        if (Check_data()) {
            try {
                JSONObject add = new JSONObject();
                //Toast.makeText(v.getContext(),"HERE",Toast.LENGTH_LONG).show();
                add.put("user_email", "testappuser@gmail.com");
                add.put("training_center_id", "t1");
                add.put("course_id", "c1");
                add.put("subject", _subject.getText().toString());
                add.put("detail", _details.getText().toString());
                add.put("rating", 3);
                new get_request(add, v.getContext()).execute();

            } catch (Exception e) {
                Log.d("ERROR", e.getMessage());


            }

            //new get_request(name, email, comment, v.getContext()).execute();

        }
    }

    boolean Check_data() {
        boolean flag = true;

        /*if (_subject == null || _subject.getText().length() <= 3) {
            _subject.setError("Name must be greater than 3 characters");
            flag = false;
        } else {
            _subject.setError(null);
        }

        if (_details == null) {
            flag = false;
            _details.setError("Invalid Email Addresss");

        } else {
            _details.setError(null);
        }
    */
        return flag;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item_course_name = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


class get_request_subject extends AsyncTask<String, Void, String> {

    boolean flag;
    String name, email, comment;
    Spinner sp_course_name;
    Context context;


    public get_request_subject(Spinner course_name, Context context) {
        this.sp_course_name = course_name;
        this.context = context;
        //Toast.makeText(context.getApplicationContext(),add.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPostExecute(String s) {


        Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_LONG).show();

    }

    @Override
    protected String doInBackground(String... params) {

        try {

            String link = context.getResources().getString(R.string.link) + "/api/coursefeedback/";
            URL url = new URL(link);
            URLConnection con = url.openConnection();

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());


            //wr.write(add.toString());
            wr.flush();


            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

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


class get_request extends AsyncTask<String, Void, String> {
    boolean flag;
    String name, email, comment;
    Context context;
    JSONObject add;

    public get_request(JSONObject add, Context context) {
        this.add = add;
        this.context = context;
        //Toast.makeText(context.getApplicationContext(),add.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPostExecute(String s) {


        Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_LONG).show();

    }

    @Override
    protected String doInBackground(String... params) {

        try {

            String link = context.getResources().getString(R.string.link) + "/api/coursefeedback/";


            //Toast.makeText(context.getApplicationContext(),"LLLasfsdfdOLLL",Toast.LENGTH_LONG).show();
            URL url = new URL(link);
            URLConnection con = url.openConnection();

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());


            wr.write(add.toString());
            wr.flush();


            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

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
