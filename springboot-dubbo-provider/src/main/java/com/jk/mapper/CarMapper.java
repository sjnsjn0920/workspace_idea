package com.jk.mapper;

import com.jk.model.Car;

import java.util.List;

public interface CarMapper {
    List<Car> queryCarList();

    void addUser(Car car);

}
