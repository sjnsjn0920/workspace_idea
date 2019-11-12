package com.jk.service;

import com.jk.model.Car;

import java.util.List;

public interface CarService {
    List<Car> querycar() throws Exception;

    void del(Integer id);

    void add(Car model);

    Car upda(Integer id);

    Car update(Car car);

    List<Car> queryTwo(String[] id) throws Exception;

    void addTwo(List<Car> list);
}
