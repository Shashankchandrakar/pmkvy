package com.sih.pmkvy.course_info;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sih.pmkvy.R;

import java.util.List;

/**
 * Created by hp on 24-03-2017.
 */

public class course_info_adapter extends RecyclerView.Adapter<course_info_adapter.Cousre_info_view_holder> {


    public static class Cousre_info_view_holder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView start_data, center_name, course_name, job_role, batch_id;

        public Cousre_info_view_holder(View itemView) {
            super(itemView);

            start_data = (TextView) itemView.findViewById(R.id.starting_data);
            center_name = (TextView) itemView.findViewById(R.id.center_name_batch);
            job_role = (TextView) itemView.findViewById(R.id.job_role_batch);
            course_name = (TextView) itemView.findViewById(R.id.course_name_batch_info);
            batch_id = (TextView) itemView.findViewById(R.id.batch_id);
        }
    }

    List<couse_info_data> courses;

    public course_info_adapter(List<couse_info_data> courses) {
        this.courses = courses;


    }

    @Override
    public Cousre_info_view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.batch_info_cardview, parent, false);
        Cousre_info_view_holder civh = new Cousre_info_view_holder(v);
        return civh;
    }

    @Override
    public void onBindViewHolder(Cousre_info_view_holder holder, int position) {


        holder.start_data.setText(courses.get(position).starting_date);
        holder.course_name.setText(courses.get(position).course_name);
        holder.center_name.setText(courses.get(position).center_name);
        holder.job_role.setText(courses.get(position).job_role);
        holder.batch_id.setText(courses.get(position).batch_id);


    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
