
package com.jk.controller;

import com.jk.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller

public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping("show")
    public String show(HttpServletRequest request){
        return "show";
    }


    @RequestMapping("queryCarAll")
    @ResponseBody
    public List<Map<String,Object>> queryCarAll(){


        List<Map<String,Object>> map =carService.queryCarAll();

        List<Map<String,Object>> map2 =new ArrayList<Map<String,Object>>();

        for (Map<String,Object> map1:map) {
                Map<String,Object> map3=new HashMap<>();
                map3.put("name",map1.get("time"));
                map3.put("data",map1.get("count"));
                map2.add(map3);

        }




        return map;
    }

    @RequestMapping("queryZhu")
    @ResponseBody
    public List<Map<String,Object>> queryZhu(){
        List<Map<String,Object>> map =carService.queryZhu();
        List<Map<String,Object>> map2 =new ArrayList<Map<String,Object>>();
        for (Map<String,Object> map1:map) {
            Map<String,Object> map3=new HashMap<>();
            map3.put("name",map1.get("time"));
            map3.put("data",map1.get("counts"));
            map2.add(map3);
        }
        return map;
    }
    @RequestMapping("querymianji")
    @ResponseBody
    public List<Map<String,Object>> querymianji(){
        List<Map<String,Object>> map =carService.querymianji();
        List<Map<String,Object>> map2 =new ArrayList<Map<String,Object>>();
        for (Map<String,Object> map1:map) {
            Map<String,Object> map3=new HashMap<>();
            map3.put("name",map1.get("time"));
            map3.put("data",map1.get("counts"));
            map2.add(map3);
        }
        return map;
    }


}
