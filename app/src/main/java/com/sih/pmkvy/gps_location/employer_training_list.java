package com.sih.pmkvy.gps_location;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.sih.pmkvy.R;
import com.sih.pmkvy.centre_complete_info.*;
import com.sih.pmkvy.adapter.ClickListener;
import com.sih.pmkvy.adapter.RecyclerTouchListener;
import com.sih.pmkvy.ui.DividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nidhi Singh on 3/22/2017.
 */
//main class file(recyclerview)
public class employer_training_list extends AppCompatActivity implements LocationListener {

    List<training_details> t_d;
    RecyclerView training_recycler_view;

    //layout(where recycler is used) and recycler id is linked here
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employe_training);
        training_recycler_view = (RecyclerView) findViewById(R.id.recyclerview_training_list);
        LinearLayoutManager t_linear = new LinearLayoutManager(this);
        training_recycler_view.setLayoutManager(t_linear);
        training_recycler_view.setItemAnimator(new DefaultItemAnimator());
        training_recycler_view.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        training_recycler_view.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), training_recycler_view, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent complete=new Intent(view.getContext(),centre_info_display.class);
                TextView center_id=(TextView)view.findViewById(R.id.training_phone_no_list);
                complete.putExtra("Phone",center_id.getText());
                startActivity(complete);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        // spinner(STATE)


        List<String> categories_state_training = new ArrayList<String>();

        categories_state_training.add("Select States");
        categories_state_training.add("chhattisgarh");
        categories_state_training.add("mp");

        ArrayAdapter<String> adapter_state_training = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories_state_training);
        adapter_state_training.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        List<String> categories_district_training = new ArrayList<String>();


        List<String> categories_state_district = new ArrayList<>();
        categories_state_district.add("Select District");


        ArrayAdapter<String> adapter_district_training = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories_state_district);
        adapter_district_training.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        List<String> categories_sector_training = new ArrayList<>();


        categories_sector_training.add("Select sectors");
        categories_sector_training.add("agriculture");
        categories_sector_training.add("medical");
        categories_sector_training.add("fire");

        ArrayAdapter<String> adapter_sector_training = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories_sector_training);
        adapter_sector_training.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //get_training_request training_req = new get_training_request(this);
        //training_req.execute();


        //spinner(JOBROLE)


        List<String> categories_jobrole_training = new ArrayList<>();


        categories_jobrole_training.add("Select Jobroles");


        ArrayAdapter<String> adapter_jobrole_training = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories_jobrole_training);
        adapter_jobrole_training.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        LocationManager locationManager;
        String mprovider;


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        mprovider = locationManager.getBestProvider(criteria, false);

        if (mprovider != null && !mprovider.equals("")) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = locationManager.getLastKnownLocation(mprovider);
            locationManager.requestLocationUpdates(mprovider, 15000, 1, this);

            if (location != null) {

                onLocationChanged(location);
                Toast.makeText(this,String.valueOf(location.getLatitude())+String.valueOf(location.getLongitude()),Toast.LENGTH_SHORT).show();
                new get_training_jobs(this, training_recycler_view, location.getLatitude(), location.getLongitude()).execute();
            } else
                Toast.makeText(getBaseContext(), "No Location Provider Found Check Your Code", Toast.LENGTH_SHORT).show();
        }


        //initializedata();
        //initializeadapter();
    }

    //contents of training list from database are added here
   /* private void initializedata() {
        t_d = new ArrayList<>();
        t_d.add(new training_details("trainingcenter1", "rajasthan_address", "675543727"));
        t_d.add(new training_details("trainingcenter2", "delhi_address", "675543727"));
        t_d.add(new training_details("trainingcenter3", "chhattisgarh_address", "675543727"));
        t_d.add(new training_details("trainingcenter4", "uttarpradesh_address", "675543727"));
        t_d.add(new training_details("trainingcenter5", "madhyapradesh_address", "675543727"));


    }

    private void initializeadapter() {
        training_data_adapter t_a_d = new training_data_adapter(t_d);
        training_recycler_view.setAdapter(t_a_d);
    }*/


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}


class get_training_jobs extends AsyncTask<String, Void, String> {
    List<training_details> t_d;
    RecyclerView rv;
    JSONObject add;
    Context context;
    boolean flag;
    String jobrole;
    double lat, lon;

    public get_training_jobs(Context context, RecyclerView rv, double lat, double lon) {
        this.rv = rv;
        this.jobrole = jobrole;
        this.context = context;
        this.lat = lat;
        this.lon = lon;

    }

    @Override
    protected void onPostExecute(String s) {
        //Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_LONG).show();
        training_data_adapter adapter;
        try {
            List<String> jobrole = new ArrayList<>();
            t_d = new ArrayList<>();
            JSONObject obj = new JSONObject(s);
            JSONArray array = obj.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                String center_name, center_address, center_id;
                center_name = jsonObject.getString("training_center_name");
                center_address = jsonObject.getString("training_center_state");
                center_id = jsonObject.getString("center_id");
                t_d.add(new training_details(center_name, center_address, center_id));

            }
            training_data_adapter t_a_d = new training_data_adapter(t_d);
            this.rv.setAdapter(t_a_d);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String link = "http://192.168.43.5:8000/api/gpstrainingcenter/";


            URL url = new URL(link);
            URLConnection con = url.openConnection();

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            add = new JSONObject();
            //JSONObject add = new JSONObject();

            add.put("tc_lat", lat);
            add.put("tc_lon", lon);


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
