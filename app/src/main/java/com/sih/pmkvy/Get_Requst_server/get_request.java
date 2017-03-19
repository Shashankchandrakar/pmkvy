package com.sih.pmkvy.Get_Requst_server;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import android.app.Activity;
/**
 * Created by ASUS on 3/19/2017.
 */

public class get_request extends AsyncTask<String,Void,String> {
    TextView data;
    Context context;

    public get_request(TextView data,Context context) {
        this.data=data;
        this.context=context;
    }

    @Override
    protected void onPostExecute(String s) {

        try
        {
            JSONObject jsonObject=new JSONObject(s);
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            JSONObject obj=jsonArray.getJSONObject(0);



        String ans=obj.getString("id");
        this.data.setText(ans);}
        catch (Exception e)
        {

        }
    }

    @Override

    protected String doInBackground(String... params) {

        try {
            String link="http://d164f07a.ngrok.io/api/trainingcenter/";
            URL url =new URL(link);
            HttpClient client=new DefaultHttpClient();
            HttpGet request= new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response=client.execute(request);
            BufferedReader in=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer stringBuffer=new StringBuffer();
            String line="";

            while ((line = in.readLine())!=null)
            {
                stringBuffer.append(line);

            }
            in.close();
            //data.setText("FS");
            Log.d("DATA",stringBuffer.toString());
            return stringBuffer.toString();


        } catch (Exception e) {
            return "Exception: " + e.getMessage();
        }

    }
}
