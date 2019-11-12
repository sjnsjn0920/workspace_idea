package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.Car;
import com.jk.service.CarService;
import com.jk.util.ExportExcel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    @Reference(version = "1.0")
    private CarService service;

    @Autowired
    private SolrClient client;

    @RequestMapping("toindex")
    public String toindex(){
        return "index";
    }

    @RequestMapping("querycar")
    @ResponseBody
    public List querycar(){

        List<Car> list=new ArrayList<>();

        try {
            list=  service.querycar();
        }catch (Exception e){
            e.printStackTrace();
        }


        return list;

    }
/*
    @RequestMapping("export")
    public void export(HttpServletResponse response){
        List<MusicModel> list= new ArrayList<MusicModel>();
        try {
            list = service.query();

            //定义表格的标题
            String title ="音乐列表";
            //定义列名
            String[] rowName={"编号","音乐名称","作者","日期","图片"};
            //定义工具类要的数据
            List<Object[]>  dataList = new ArrayList<Object[]>();

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");


            //循环数据把数据存放到 List<Object[]>
            for (MusicModel model:list) {
                Object[] obj=new Object[rowName.length];
                obj[0]=model.getId();
                obj[1]=model.getMname();
                obj[2]=model.getMauthor();
                obj[3]=sdf.format(model.getMdate());
                obj[4]=model.getMimg();

                dataList.add(obj);
            }
            ExportExcel exportExcel=new ExportExcel(title,rowName,dataList,response);
            exportExcel.export();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/

    @RequestMapping("Export")
    public void Export(HttpServletResponse response,String[] id){

        if(id.length<=0){

            List<Car> list= new ArrayList<Car>();
            try {
                list = service.querycar();

                String title ="汽车列表";

                String[] rowName={"编号","车名","价格","日期"};

                List<Object[]>  dataList = new ArrayList<Object[]>();

                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

                for (Car model:list) {
                    Object[] obj=new Object[rowName.length];
                    obj[0]=model.getCarId();
                    obj[1]=model.getCarName();
                    obj[2]=model.getCarPrice();
                    obj[3]=model.getCarTime();


                    dataList.add(obj);
                }
                ExportExcel exportExcel=new ExportExcel(title,rowName,dataList,response);
                exportExcel.export();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }else{

            List<Car> list= new ArrayList<Car>();

            try {
                list = service.queryTwo(id);


                String title ="汽车列表";

                String[] rowName={"编号","车名","价格","日期"};

                List<Object[]>  dataList = new ArrayList<Object[]>();



                for (Car model:list) {
                    Object[] obj=new Object[rowName.length];

                    obj[0]=model.getCarId();
                    obj[1]=model.getCarName();
                    obj[2]=model.getCarPrice();
                    obj[3]=model.getCarTime();

                    dataList.add(obj);
                }
                ExportExcel exportExcel=new ExportExcel(title,rowName,dataList,response);
                exportExcel.export();

            } catch (Exception e) {
                e.printStackTrace();
            }




        }


    }


    @RequestMapping("import")
    public String Import(MultipartFile file, HttpServletResponse response){

        //上传文件的名称
        String fileName = file.getOriginalFilename();

        System.out.println(fileName);

        //生成新的文件名称
        String filePath = "../src/main/resources/templates/fileupload/";

        //创建list集合接收传递的参数
        List<Car> list= new ArrayList<Car>();

        //上传文件
        try {
            uploadFile(file.getBytes(), filePath, fileName);


            //通过文件创建流
            FileInputStream fileInputStream = new FileInputStream(filePath+fileName);
            //创建操作excel的对象   因为xls的文件需要HSSFWorkbook  xlsx需要的XSSFWorkbook 因此直接使用workBook对象就行了
            Workbook workbook= WorkbookFactory.create(fileInputStream) ;
            //通过workbook获得sheet页  sheet有可能有多个
            for(int i=0;i<workbook.getNumberOfSheets();i++){
                //创建sheet对象
                Sheet sheetAt = workbook.getSheetAt(i);
                //根绝sheet创建行  row
                for(int j=3;j<sheetAt.getPhysicalNumberOfRows();j++){
                    //创建row对象
                    Row row = sheetAt.getRow(j);

                    //创建对象接收从文件读取的内容
                    Car model = new Car();
                    if( !"".equals(row.getCell(1)) && row.getCell(1)!=null){
                        model.setCarName(row.getCell(1).toString());
                    }
                    if( !"".equals(row.getCell(2)) && row.getCell(2)!=null){
                        model.setCarPrice(row.getCell(2).toString());
                    }

                    if( !"".equals(row.getCell(3)) && row.getCell(3)!=null){
                        model.setCarTime(row.getCell(3).toString());
                    }




                    list.add(model);
                }

            }
            service.addTwo(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "index";

    }








    //上传文件的方法
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }






    @RequestMapping("del")
    @ResponseBody
    public String del(Integer id){

        service.del(id);
        try {
            client.deleteById("core1",id);
            client.commit("core1");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    @RequestMapping("add")
    @ResponseBody
    public String add(Car car){

        service.add(car);
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("carId", car.getCarId());
            doc.setField("carName",car.getCarName() );
            doc.setField("carTime",car.getCarTime());
            doc.setField("carPrice",car.getCarPrice());



            client.add("core1", doc);
            //client.commit();
            client.commit("core1");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    @RequestMapping("upda")
    @ResponseBody
    public Car upda(Integer id){

        Car car =  service.upda(id);

        return car;

    }

    @RequestMapping("update")
    @ResponseBody
    public String update(Car car){

        service.update(car);
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("carId", car.getCarId());
            doc.setField("carName",car.getCarName() );
            doc.setField("carTime",car.getCarTime());
            doc.setField("carPrice",car.getCarPrice());


            client.add("core1", doc);
            //client.commit();
            client.commit("core1");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }









}
