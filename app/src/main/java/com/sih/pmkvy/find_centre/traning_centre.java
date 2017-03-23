package com.sih.pmkvy.find_centre;

import com.sih.pmkvy.database.databse_handler_training_centre;
import com.sih.pmkvy.database.sqlite_training_centre_data;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.sih.pmkvy.R;
import com.sih.pmkvy.adapter.ClickListener;
import com.sih.pmkvy.adapter.RecyclerTouchListener;
import com.sih.pmkvy.adapter.centre_list_adapter;
import com.sih.pmkvy.ui.DividerItemDecoration;
import com.sih.pmkvy.centre_complete_info.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by test on 2/26/2017.
 */


public class traning_centre extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private List<find_centre> centre_list = new ArrayList<>();
    private RecyclerView recyclerView;
    private centre_list_adapter centre_lists_adapter;
    Button call_button;
    Spinner district_spinner, state_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traning_centre_activity_layout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        centre_lists_adapter = new centre_list_adapter(centre_list);

        district_spinner = (Spinner) findViewById(R.id.district_training_center);
        state_spinner = (Spinner) findViewById(R.id.state_training_center);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(centre_lists_adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {

            public void onClick(View view, int position) {

                //TODO : handle on click event here
                Intent centre_info_intent = new Intent(view.getContext(), centre_info_display.class);
                TextView phone = (TextView) view.findViewById(R.id.centre_phone_no);
                centre_info_intent.putExtra("Phone", phone.getText());
                startActivity(centre_info_intent);


                //find_centre centre = centre_list.get(position);
                //Toast.makeText(getApplicationContext(), centre.getCentre_name() + " is selected!", Toast.LENGTH_SHORT).show();
                // TextView centre_info = (TextView) view.findViewById(R.id.centre_info);
                //Button call_button = (Button) view.findViewById(R.id.call);
                // Toast.makeText(getApplicationContext(), String .valueOf(view.getId()),Toast.LENGTH_SHORT).show();
                //if (view.getId() == R.id.card_view)
                /*{

                    if (centre_info.getVisibility() == View.GONE) {

                    }
                     /*else {
                        centre_info.setVisibility(View.GONE);
                        call_button.setVisibility(View.GONE);
                    }/

                }*/


            }


            public void onLongClick(View view, int position) {

            }
        }));
        setCentre_list();
        district_spinner.setOnItemSelectedListener(this);
        state_spinner.setOnItemSelectedListener(this);

        List<String> state = new ArrayList<String>();
        state.add("CG");
        state.add("MP");

        ArrayAdapter<String> state_dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, state);
        state_spinner.setAdapter(state_dataAdapter);

        List<String> district = new ArrayList<String>();
        district.add("Durg");
        district.add("Raipur");

        ArrayAdapter<String> district_dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, district);
        district_spinner.setAdapter(district_dataAdapter);

    }

    private void setCentre_list() {
            /*find_centre centre =new find_centre("TEST CENTRE NAME","ADDRESS is HERE","12","Some Random Info","CALL");
            centre_list.add(centre);
            centre =new find_centre("TEST 1 CENTRE NAME","ADDRESS is HERE","214","Some Random Info","CALL");
            centre_list.add(centre);
            centre =new find_centre("TEST 1 2 CENTRE NAME","ADDRESS is HERE","3425","Some Random Info","CALL");
            centre_list.add(centre);
            centre =new find_centre("TEST 1 3 CENTRE NAME","ADDRESS is HERE","325","Some Random Info","CALL");
            centre_list.add(centre);
            centre =new find_centre("TEST 1  4 CENTRE NAME","ADDRESS is HERE","325","Some Random Info","CALL");
            centre_list.add(centre);
            centre =new find_centre("TEST 1  2 2 CENTRE NAME","ADDRESS is HERE","796","Some Random Info","CALL");
            centre_list.add(centre);
            centre =new find_centre("TEST 1e CENTRE NAME","ADDRESS is HERE","658","Some Random Info","CALL");
            centre_list.add(centre);
            centre =new find_centre("TEST 1r CENTRE NAME","ADDRESS is HERE","976","Some Random Info","CALL");
            centre_list.add(centre);
            */
        //find_centre centre_add;
        //databse_handler_training_centre db = new databse_handler_training_centre(this);
        Log.d("INSERT: ", "Inserting ..");


        //TODO:remove this line after first run
        //db.addCentre(new sqlite_training_centre_data( "SOME RANDOM I 23NFO", "SOME RANDOM AD 2DRESS", "SO234ME RANDOM NAME", 1232354));
        //db.addCentre(new sqlite_training_centre_data( "SOME RANDOM 23INFO", "SOME RANDOM 324ADDRESS", "SOME2 3 RANDOM NAME", 1234235));
        // db.addCentre(new sqlite_training_centre_data( "SOME RANDOM IN23FO", "SOME RANDOM234 ADDRESS", "SOME RAN234DOM NAME", 123235));
        //db.addCentre(new sqlite_training_centre_data( "SOME RANDOM 324 INFO", "SOME RANDOM AD324 DRESS", "SOME RA432 NDOM NAME", 3123532));

        //Log.d("READING: ", "Reading all centre ...");

        //List<sqlite_training_centre_data> centres = db.getAllCentreInfo();

        /*for (sqlite_training_centre_data centre1 : centres) {
            centre_add = new find_centre(centre1.getCentre_name(), centre1.getCentre_address(), String.valueOf(centre1.getCentre_phone_no())
                    , centre1.getCentre_info());

            centre_list.add(centre_add);

        }*/
        get_request a = new get_request(getApplicationContext(), centre_list, centre_lists_adapter);
        a.execute();

        //centre_lists_adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


class get_request extends AsyncTask<String, Void, String> {
    String res;
    JSONObject json;
    boolean flag;
    Context context;
    List<find_centre> center_list;
    centre_list_adapter centre_lists_adapter;

    public get_request(Context context, List<find_centre> center_list, centre_list_adapter centre_lists_adapter) {
        this.context = context;
        this.centre_lists_adapter = centre_lists_adapter;
        this.center_list = center_list;

    }

    @Override
    protected void onPostExecute(String s) {
        //Toast.makeText(context.getApplicationContext(), res+s, Toast.LENGTH_LONG).show();
        StringBuilder center_name;
        StringBuilder center_address;
        StringBuilder center_phone;
        StringBuilder center_info;
        find_centre centre_add;
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);
                center_name = new StringBuilder(obj.getString("training_center_name"));
                center_address = new StringBuilder(obj.getString("address"));
                center_phone = new StringBuilder(obj.getString("center_id"));
                center_info = new StringBuilder(obj.getString("center_id"));
                centre_add = new find_centre(center_name.toString(), center_address.toString(), center_phone.toString(), center_info.toString());
                center_list.add(centre_add);

            }
            //this.data.setText(ansobj.toString());
            //result=new String(ansobj.toString());
            centre_lists_adapter.notifyDataSetChanged();
        } catch (Exception e) {

        }
    }

    @Override

    protected String doInBackground(String... params) {

        try {

            String link = "http://192.168.19.50:8000/api/trainingcenter/";

            //String data= "{'user_name':'name','user_password':'pass','user_email':'email'}";


            URL url = new URL(link);
            URLConnection con = url.openConnection();
            //Toast.makeText(context.getApplicationContext(),"LLLasfdOLLL",Toast.LENGTH_LONG).show();
            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            json = new JSONObject();
            JSONObject add = new JSONObject();

            add.put("training_center_district", "durg");

            //json.put("data", add);


            wr.write(add.toString());
            wr.flush();
            res=add.toString();
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
