
package com.jk.model;

import java.io.Serializable;


public class Car implements Serializable{

    private static final long serialVersionUID = 8506191501202450805L;

    private Integer carId;


    private String carName;


    private String  carTime;



    private String carPrice;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarTime() {
        return carTime;
    }

    public void setCarTime(String carTime) {
        this.carTime = carTime;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }
}
