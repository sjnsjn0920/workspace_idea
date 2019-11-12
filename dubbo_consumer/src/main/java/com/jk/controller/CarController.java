
package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.Car;
import com.jk.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class CarController {

    @Reference
    private CarService service;

    @RequestMapping("show")
    public String show(){
        return "index";
    }
    @RequestMapping("queryCarList")
    @ResponseBody
    public List<Car> queryCarList(HttpServletRequest request){

        List<Car> list= service.queryCarList();

        return list;
    }
    @RequestMapping("addUser")
    @ResponseBody
    public void addUser(Car car){
        service.addUser(car);
    }
    @RequestMapping("delall")
    @ResponseBody
    public void delall(Integer id){
        service.delall(id);
    }
    @RequestMapping("openUpdate")
    @ResponseBody
    public Car openUpdate(Integer id){

        return service.openUpdate(id);
    }

}
