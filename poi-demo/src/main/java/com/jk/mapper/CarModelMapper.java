package com.jk.mapper;

import com.jk.model.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CarModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);


    List<Car> query();

    void del(Integer id);

    void add(Car model);

    Car upda(@Param("id") Integer id);

    void update(Car model);

    List<Car> queryTwo(@Param("ida") String[] id);

    void addTwo(List<Car> list);
}