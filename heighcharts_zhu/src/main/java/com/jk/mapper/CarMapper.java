
package com.jk.mapper;

import com.jk.model.Car;

import java.util.List;
import java.util.Map;


public interface CarMapper {


    List<Map<String,Object>> queryCarAll();

    List<Map<String, Object>> queryZhu();

    List<Map<String, Object>> querymianji();
}
