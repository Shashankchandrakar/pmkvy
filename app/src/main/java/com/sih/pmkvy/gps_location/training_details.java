package com.sih.pmkvy.gps_location;

/**
 * Created by Nidhi Singh on 3/22/2017.
 */

public class training_details {

    String training_center_name,training_center_address,training_center_phn;

    training_details(String training_center_name,String training_center_address,String training_center_phn)
    {
        this.training_center_name = training_center_name ;
        this.training_center_address = training_center_address;
        this.training_center_phn = training_center_phn;

    }

    public String getTraining_center_name(){ return training_center_name ; }
    public void setTraining_center_name(String training_center_name)
    {
        this.training_center_name = training_center_name;
    }

    public String getTraining_center_address(){return training_center_address;}
    public void setTraining_center_address(String training_center_address)
    {
        this.training_center_address = training_center_address;
    }

    public String getTraining_center_phn(){return training_center_phn;}
    public void setTraining_center_phn(String training_center_phn)
    {
        this.training_center_phn = training_center_phn;
    }

}
