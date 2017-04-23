package com.sih.pmkvy.batch_info;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.sih.pmkvy.R;

import org.json.JSONArray;
import org.json.JSONException;
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

public class batch_info extends AppCompatActivity {
    TextView center_name, course_name, job_role, job_sector, no_of_seats, last_date, assessment_date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_batch_info);
        Bundle b = getIntent().getExtras();
        String batch_id = b.getString("batch_id");
        new get_request(this, batch_id, this).execute();
        center_name = (TextView) findViewById(R.id.bd_center_name);
        course_name = (TextView) findViewById(R.id.bd_course_name);
        job_role = (TextView) findViewById(R.id.bd_jobrole);
        job_sector = (TextView) findViewById(R.id.bd_jobsector);
        no_of_seats = (TextView) findViewById(R.id.bd_no_of_seats);
        last_date = (TextView) findViewById(R.id.bd_lastdate);
        assessment_date = (TextView) findViewById(R.id.bd_assessement_date);


    }
}

class get_request extends AsyncTask<String, Void, String> {
    Context context;
    String batch_id;
    boolean flag;
    batch_info batch;

    public get_request(Context context, String batch_id, batch_info batch) {
        this.context = context;
        this.batch_id = batch_id;
        this.batch = batch;
    }

    @Override
    protected void onPostExecute(String s) {
        //Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_SHORT).show();;
        try {
            JSONObject obj;
            obj = new JSONObject(s);

            JSONArray arr = obj.getJSONArray("data");
            JSONObject obj1 = arr.getJSONObject(0);

            batch.center_name.setText(obj1.getJSONObject("training_center_id").getString("training_center_name"));
            batch.course_name.setText(obj1.getJSONObject("course_id").getString("course_name"));
            batch.job_role.setText(obj1.getJSONObject("training_center_id").getString("job_role_name"));
            batch.job_sector.setText(obj1.getJSONObject("training_center_id").getString("sector_skill_council"));
            batch.no_of_seats.setText(obj1.getJSONObject("training_center_id").getString("target_allocated"));
            batch.last_date.setText(obj1.getString("batch_last_date_registration"));
            batch.assessment_date.setText(obj1.getString("batch_assessment_date"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //ba= new ArrayAdapter<String>(this, R.layout.spinner_item, value);
    }

    @Override

    protected String doInBackground(String... params) {

        try {
            String link = context.getResources().getString(R.string.link) + "/api/batchinfolist/";

            URL url = new URL(link);
            URLConnection con = url.openConnection();

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            JSONObject add = new JSONObject();

            add.put("batch_id", batch_id);

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
