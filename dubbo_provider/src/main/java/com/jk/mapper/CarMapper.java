
package com.jk.mapper;

import com.jk.model.Car;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface CarMapper {
    List<Car> queryCarList();

    void addUser(Car car);

    void delall(Integer id);

    Car openUpdate(Integer id);
    @Update(" update t_car set carName=#{carName},carTime=#{carTime},carPrice=#{carPrice} where carId=#{carId}")
    void update(Car car);
}
