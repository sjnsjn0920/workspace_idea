package com.jk.service;

import com.jk.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService{
    @Autowired
    private CarMapper carMapper;
    @Override
    public List<Map<String, Object>> queryCar() {

        return carMapper.queryCar();
    }
}
