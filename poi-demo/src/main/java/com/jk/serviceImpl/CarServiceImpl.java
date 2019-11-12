package com.jk.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.mapper.CarModelMapper;
import com.jk.model.Car;
import com.jk.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Service(version = "1.0")
public class CarServiceImpl implements CarService {

    @Autowired
    private CarModelMapper carModelMapper;


    @Override
    public List<Car> query() throws Exception {
        return carModelMapper.query();
    }

    @Override
    public void del(Integer id) {

        carModelMapper.del(id);
    }



    @Override
    public void add(Car car) {

        carModelMapper.add(car);

    }

    @Override
    public Car upda(Integer id) {
        return carModelMapper.upda(id);
    }



    @Override
    public void update(Car car) {

        carModelMapper.update(car);
    }

    @Override
    public List<Car> queryTwo(String[] id) throws Exception {
        return carModelMapper.queryTwo(id);

    }



    @Override
    public void addTwo(List<Car> list) {

        carModelMapper.addTwo(list);
    }
}
