package com.sih.pmkvy.gps_location;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.sih.pmkvy.R;

import java.util.List;

/**
 * Created by Nidhi Singh on 3/22/2017.
 */
//CardView is linked with RecyclerView
public class training_data_adapter extends RecyclerView.Adapter<training_data_adapter.training_view_holder>{


    public static class training_view_holder extends RecyclerView.ViewHolder{


        CardView t_cv;
        TextView training_center_name,training_center_address,training_center_phn;

        public training_view_holder(View trainingView) {
            super(trainingView);
            t_cv = (CardView) trainingView.findViewById(R.id.training_card_view);
            training_center_name = (TextView) trainingView.findViewById(R.id.training_name_list);
            training_center_address = (TextView) trainingView.findViewById(R.id.training_address_list);
            training_center_phn = (TextView) trainingView.findViewById(R.id.training_phone_no_list);

        }
    }

    List<training_details> t_r_d;
    training_data_adapter(List<training_details> t_r_d){
        this.t_r_d = t_r_d;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public training_view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_center_info,parent,false);
        training_view_holder tvd = new training_view_holder(v);
        return tvd;
    }

    @Override
    public void onBindViewHolder(training_view_holder holder, int position) {
         holder.training_center_name.setText(t_r_d.get(position).getTraining_center_name());
         holder.training_center_address.setText(t_r_d.get(position).getTraining_center_address());
         holder.training_center_phn.setText(t_r_d.get(position).getTraining_center_phn());
    }

    @Override
    public int getItemCount() {return t_r_d.size();}
}
