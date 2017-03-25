package com.sih.pmkvy.course_info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sih.pmkvy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 24-03-2017.
 */

public class course_info_activity extends AppCompatActivity {

    List<couse_info_data> course;
    RecyclerView rv;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.course_info);

        rv=(RecyclerView)findViewById(R.id.recyclerview1);
        LinearLayoutManager linearlayout=new LinearLayoutManager(this);
        rv.setLayoutManager(linearlayout);

        InitializedData();
        InitializeAdapter();

    }
    private void InitializedData()
    {
        course=new ArrayList<>();
        course.add(new couse_info_data("12/03/85","30/04/86","20","Availaible"));
        course.add(new couse_info_data("15/05/85","30/08/86","40","Availaible"));
        course.add(new couse_info_data("20/08/85","30/10/86","25","Availaible"));


    }
    private void InitializeAdapter(){
        course_info_adapter cid=new course_info_adapter(course);
        rv.setAdapter(cid);
        cid.notifyDataSetChanged();

    }
}
