package com.sih.pmkvy.feedback;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sih.pmkvy.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by hp on 25-03-2017.
 */

public class feedback extends AppCompatActivity implements View.OnClickListener {
    EditText _email, _name, _comment;
    Button b1;
    String email, name, comment;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        this.setTitle("Feedback");
        _name = (EditText) findViewById(R.id.name);
        _email = (EditText) findViewById(R.id.email_feedback);
        _comment = (EditText) findViewById(R.id.comment);

        b1 = (Button) findViewById(R.id.button);

        email = _email.getText().toString();
        name = _name.getText().toString();
        comment = _comment.getText().toString();


        b1.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (Check_data()) {

            new get_request(name,email,comment,v.getContext()).execute();

        }
    }

    boolean Check_data() {
        boolean flag = true;

        if (name == null || name.length() <= 3) {
            _name.setError("Name must be greater than 3 characters");
            flag = false;
        } else {
            _name.setError(null);
        }

        if (email == null || !android.util.Patterns.EMAIL_ADDRESS.matcher(_email.getText()).matches()) {
            flag = false;
            _email.setError("Invalid Email Addresss");

        } else {
            _email.setError(null);
        }

        if (comment == null || comment.length() <= 1) {
            flag = false;
            _comment.setError("Enter Comment");

        } else {
            _comment.setError(null);
        }
        return flag;
    }
}




class get_request extends AsyncTask<String,Void,String> {
    boolean flag;
    String name,email,comment;
    Context context;

    public get_request(String name,String email,String comment,Context context) {

        this.name=name;
        this.email=email;
        this.comment=comment;
        this.context=context;
    }

    @Override
    protected void onPostExecute(String s) {


        Toast.makeText(context.getApplicationContext(),s,Toast.LENGTH_LONG).show();

    }

    @Override
    protected String doInBackground(String... params) {

        try {

            String link="http://20a770be.ngrok.io/api/coursefeedback/";




            //Toast.makeText(context.getApplicationContext(),"LLLasfsdfdOLLL",Toast.LENGTH_LONG).show();
            URL url=new URL(link);
            URLConnection con=url.openConnection();

            con.setDoOutput(true);
            OutputStreamWriter wr=new OutputStreamWriter(con.getOutputStream());


            JSONObject add=new JSONObject();

            add.put("user_email","testappuser@gmail.com");
            add.put("training_center_id","t1");
            add.put("course_id","c1");
            add.put("subject",name);
            add.put("detail",comment);
            add.put("rating",3);






            wr.write(add.toString());
            wr.flush();




            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder sb=new StringBuilder();
            String line=null;

            while ((line=reader.readLine())!=null)
            {
                Log.d("LINE : ",line);
                if(line.equals("true"))
                {
                    //TODO: 3/20/2017 add response checking from server format is in jason
                    flag=true;
                }
                else
                    flag=false;

                sb.append(line);
            }
            return sb.toString();



        } catch (Exception e) {
            Log.d("ERROR",e.getMessage());
            return "Exception: " + e.getMessage();
        }

    }
}
