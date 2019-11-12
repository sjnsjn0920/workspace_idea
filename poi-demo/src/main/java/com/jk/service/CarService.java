package com.jk.service;

import com.jk.model.Car;

import java.util.List;

public interface CarService {
    List<Car> query() throws Exception;

    void del(Integer id);
    void add(Car car);

    Car upda(Integer id);
    void update(Car model);

    List<Car> queryTwo(String[] id) throws Exception;

    void addTwo(List<Car> list);



}
