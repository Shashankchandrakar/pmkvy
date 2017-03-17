package com.sih.pmkvy.find_centre;

import com.sih.pmkvy.database.databse_handler_training_centre;
import com.sih.pmkvy.database.sqlite_training_centre_data;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.sih.pmkvy.R;
import com.sih.pmkvy.adapter.ClickListener;
import com.sih.pmkvy.adapter.RecyclerTouchListener;
import com.sih.pmkvy.adapter.centre_list_adapter;
import com.sih.pmkvy.ui.DividerItemDecoration;
import com.sih.pmkvy.centre_complete_info.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by test on 2/26/2017.
 */


public class traning_centre extends AppCompatActivity {

    private List<find_centre> centre_list = new ArrayList<>();
    private RecyclerView recyclerView;
    private centre_list_adapter centre_lists_adapter;
    Button call_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traning_centre_activity_layout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        centre_lists_adapter = new centre_list_adapter(centre_list);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(centre_lists_adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {

            public void onClick(View view, int position) {

                //TODO : handle on click event here
                Intent centre_info_intent = new Intent(view.getContext(),centre_info_display.class);
                TextView phone=(TextView)view.findViewById(R.id.centre_phone_no);
                centre_info_intent.putExtra("Phone",phone.getText());
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
        find_centre centre_add;
        databse_handler_training_centre db = new databse_handler_training_centre(this);
        Log.d("INSERT: ", "Inserting ..");
        //TODO:remove this line after first run
        db.addCentre(new sqlite_training_centre_data( "SOME RANDOM I 23NFO", "SOME RANDOM AD 2DRESS", "SO234ME RANDOM NAME", 1232354));
        db.addCentre(new sqlite_training_centre_data( "SOME RANDOM 23INFO", "SOME RANDOM 324ADDRESS", "SOME2 3 RANDOM NAME", 1234235));
        db.addCentre(new sqlite_training_centre_data( "SOME RANDOM IN23FO", "SOME RANDOM234 ADDRESS", "SOME RAN234DOM NAME", 123235));
        db.addCentre(new sqlite_training_centre_data( "SOME RANDOM 324 INFO", "SOME RANDOM AD324 DRESS", "SOME RA432 NDOM NAME", 3123532));

        Log.d("READING: ", "Reading all centre ...");

        List<sqlite_training_centre_data> centres = db.getAllCentreInfo();

        for (sqlite_training_centre_data centre1 : centres) {
            centre_add = new find_centre(centre1.getCentre_name(), centre1.getCentre_address(), String.valueOf(centre1.getCentre_phone_no())
                    , centre1.getCentre_info());

            centre_list.add(centre_add);

        }

        centre_lists_adapter.notifyDataSetChanged();
    }


}


