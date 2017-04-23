package com.sih.pmkvy.course_info;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sih.pmkvy.adapter.ClickListener;
import com.sih.pmkvy.adapter.RecyclerTouchListener;
import com.sih.pmkvy.batch_info.batch_info;
import com.sih.pmkvy.register_course.*;
import com.sih.pmkvy.R;
import com.sih.pmkvy.about_pmkvy.about_pmkvy;
import com.sih.pmkvy.browse_course.browse_course;
import com.sih.pmkvy.find_centre.traning_centre;
import com.sih.pmkvy.settings.settings;

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
 * Created by hp on 24-03-2017.
 */

public class course_info_activity extends AppCompatActivity implements View.OnClickListener {

    List<couse_info_data> course;
    RecyclerView rv;
    Button reg;
    String center_id, courseid, job_role_name, job_sector, coursename, center_name;
    TextView course_name, course_id, skill_sector, job_role;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.course_info);
        reg = (Button) findViewById(R.id.reg_aadhar);
        reg.setOnClickListener(this);


        rv = (RecyclerView) findViewById(R.id.recyclerview1);
        LinearLayoutManager linearlayout = new LinearLayoutManager(this);
        rv.setLayoutManager(linearlayout);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        rv.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rv, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent batch = new Intent(view.getContext(), batch_info.class);
                TextView batch_id=(TextView)view.findViewById(R.id.batch_id);
                batch.putExtra("batch_id",batch_id.getText());
                view.getContext().startActivity(batch);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        Bundle b = getIntent().getExtras();

        center_id = b.getString("training_center_id");
        courseid = b.getString("course_id");
        center_name = b.getString("center_name");
        job_role_name = b.getString("job_role_name");
        job_sector = b.getString("job_sector");
        coursename = b.getString("course_name");


        course = new ArrayList<>();

        course_name = (TextView) findViewById(R.id.course_name_courseinfo);
        course_name.setText(coursename);

        job_role = (TextView) findViewById(R.id.job_role_courseinfo);
        job_role.setText(job_role_name);

        //course_id = (TextView) findViewById(R.id.course_id_courseinfo);
        //course_id.setText(courseid);

        //skill_sector = (TextView) findViewById(R.id.skill_sector_courseinfo);
        //skill_sector.setText(job_sector);


        new get_request(this, course, rv, center_id, courseid, course_name
                , job_role, job_sector, center_name).execute();


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
        startActivity(new Intent(this, regsiter_home.class));

    }
}

class get_request extends AsyncTask<String, Void, String> {
    Context context;
    List<couse_info_data> course;
    RecyclerView rv;
    String center_id, course_id, job_role_name, job_sector, course_name, center_name;
    JSONObject json;
    Boolean flag;
    TextView course_name_t, course_id_t, skill_sector_t, job_role_t;


    public get_request(Context context, List<couse_info_data> course, RecyclerView rv, String center_id, String course_id
            , TextView course_name_t, TextView job_role_t,
                       String job_sector, String center_name) {
        this.context = context;
        this.job_sector = job_sector;
        //this.course_name = course_name;
        this.center_name = center_name;
        this.course_name_t = course_name_t;
        // this.course_id_t = course_id_t;
        //this.skill_sector_t = skill_sector_t;
        this.course = course;
        this.rv = rv;
        this.job_role_t = job_role_t;
        this.center_id = center_id;
        this.course_id = course_id;
        //this.job_role_name = job_role_name;

    }

    @Override
    protected void onPostExecute(String s) {

        //TODO: Add validate check so that result is true or not
        String start, course_name;
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            Log.d("ERROR", String.valueOf(jsonArray.length()));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                start = obj.getString("batch_start_date");
                course_name = obj.getJSONObject("course_id").getString("course_name");
                course.add(new couse_info_data("Starts From : " + start + "End on : " + obj.getString("batch_end_date"), job_role_t.getText().toString(), center_name, course_name, obj.getString("id")));
                //Toast.makeText(context.getApplicationContext(), course.toString(), Toast.LENGTH_SHORT).show();
            }


            course_info_adapter cid = new course_info_adapter(course);
            cid.notifyDataSetChanged();
            rv.setAdapter(cid);


        } catch (Exception e) {
            Log.d("ERROR", e.toString());

        }

    }

    @Override

    protected String doInBackground(String... params) {

        try {
            String link = context.getResources().getString(R.string.link) + "/api/batchinfocourse/";

            URL url = new URL(link);
            URLConnection con = url.openConnection();

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            json = new JSONObject();
            JSONObject add = new JSONObject();

            //add.put("job_role_name", job_role_name);
            add.put("training_center_id", center_id);
            add.put("course_id", course_id);

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
