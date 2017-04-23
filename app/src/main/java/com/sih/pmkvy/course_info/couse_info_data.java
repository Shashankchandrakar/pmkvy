package com.sih.pmkvy.course_info;

/**
 * Created by hp on 24-03-2017.
 */

public class couse_info_data {
    public couse_info_data(String starting_date, String job_role, String center_name,String course_name,String batch_id) {
        this.starting_date = starting_date;
        this.job_role = job_role;
        this.center_name = center_name;
        this.course_name = course_name;
        this.batch_id=batch_id;
    }

    public String starting_date,job_role,center_name,course_name,batch_id;

}
