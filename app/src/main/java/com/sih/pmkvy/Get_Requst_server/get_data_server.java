package com.sih.pmkvy.Get_Requst_server;

import android.app.Activity;
import android.bluetooth.le.ScanRecord;
import android.os.Bundle;
import android.widget.TextView;

import com.sih.pmkvy.R;

/**
 * Created by ASUS on 3/19/2017.
 */
public class get_data_server extends Activity {
    TextView data;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String s="FSFSFS";
        //
        setContentView(R.layout.get_request);
        data=(TextView)findViewById(R.id.get_request_data);
        get_request a = new get_request(data,getApplicationContext());
        a.execute();
        data.setText(a.result);

        //data.setText(s);
        //
    }
}
