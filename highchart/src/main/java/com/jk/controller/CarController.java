package com.jk.controller;

import com.jk.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @RequestMapping("show")
    public String show(){

        System.out.println(1111111);
        System.out.println(2222222);
        return "show";
    }
    @RequestMapping("queryCar")
    @ResponseBody
    public List<Map<String,Object>> queryCar(){
        //查询数据库数据
        List<Map<String,Object>> map1 =carService.queryCar();
        //前台展示的返回的数据
        List<Map<String,Object>> map2 =new ArrayList<Map<String,Object>>();

        for (Map<String,Object> map:map1) {
            Map<String,Object> map3=new HashMap<>();
            map3.put("y",map.get("y"));
            map3.put("name",map.get("time"));
            map2.add(map3);
        }


        return map2;
    }


}
