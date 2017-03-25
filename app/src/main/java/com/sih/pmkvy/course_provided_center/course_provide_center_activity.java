package com.sih.pmkvy.course_provided_center;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.sih.pmkvy.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 3/25/2017.
 */

public class course_provide_center_activity extends AppCompatActivity {
    String center_id;

    List<center_provide_center_data> course;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_provided_by_center);
        rv = (RecyclerView) findViewById(R.id.recycler_view_course);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        course = new ArrayList<>();

        Bundle b = getIntent().getExtras();
        center_id = b.getString("center_id");
        new get_request(this, course, rv, center_id).execute();
    }

}

class get_request extends AsyncTask<String, Void, String> {
    Context context;
    List<center_provide_center_data> course;
    RecyclerView rv;
    String center_id;
    JSONObject json;
    Boolean flag;

    public get_request(Context context, List<center_provide_center_data> course, RecyclerView rv, String center_id) {
        this.context = context;
        this.course = course;
        this.rv = rv;
        this.center_id = center_id;
    }

    @Override
    protected void onPostExecute(String s) {

        course.add(new center_provide_center_data("ISRO", "Data Scientist", "Data Analysation Algorithms"));


        String job_sector, job_role, course_name;
        //TODO: Add validate check so that result is true or not

        try {
            JSONObject obj1 = new JSONObject(s);

            JSONArray obj2 = obj1.getJSONArray("data");


            for (int i = 0; i < obj2.length(); i++) {
                JSONObject obj3 = obj2.getJSONObject(i);
                JSONObject obj4 = obj3.getJSONObject("training_center_id");

                job_sector = obj4.getString("sector_skill_council");
                job_role = obj4.getString("job_role_name");
                course_name = obj3.getJSONObject("course_id").getString("course_name");
                Toast.makeText(context.getApplicationContext(),job_sector,Toast.LENGTH_LONG).show();
                course.add(new center_provide_center_data(job_sector, job_role, course_name));


            }

        } catch (Exception e) {
            Log.d("ERROR", e.toString());

        }
        course_provide_center_adapter cpca = new course_provide_center_adapter(course);
        rv.setAdapter(cpca);
    }

    @Override

    protected String doInBackground(String... params) {

        try {
            String link = context.getResources().getString(R.string.link) + "/api/fetchtrainingcentercourse/";

            URL url = new URL(link);
            URLConnection con = url.openConnection();

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            json = new JSONObject();
            JSONObject add = new JSONObject();

            add.put("training_center_id", center_id);

            wr.write(add.toString());
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line ;

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
