package com.darkfighter.basicui;

/**
 * Created by hp-pc on 14-09-2016.
 */
public class Cars {
    private String car_no,car_name;

    public Cars(String car_no,String car_name) {
        this.car_no = car_no;
        this.car_name = car_name;
    }

    public String getCar_no() {
        return car_no;
    }

    public void setCar_no(String car_no) {
        this.car_no = car_no;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }
}
