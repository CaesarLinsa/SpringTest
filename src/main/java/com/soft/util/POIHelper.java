package com.soft.util;

import com.soft.entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class POIHelper {

    private XSSFWorkbook wb;

    private String sheetName;

    private  XSSFSheet sheet;

    private int RowSum;

    private int CellSum;

    public POIHelper() {
    }

    public POIHelper(XSSFWorkbook wb, String sheetName) {
        this.wb = wb;
        this.sheet = wb.getSheet(sheetName);
        this.sheetName = sheetName;
        this.RowSum = sheet.getPhysicalNumberOfRows();
        this.CellSum =  sheet.getRow(0).getPhysicalNumberOfCells();
    }

    public void setWb(XSSFWorkbook wb) {
        this.wb = wb;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public void setSheet(XSSFSheet sheet) {
        this.sheet = sheet;
    }

    public void setRowSum(int rowSum) {
        RowSum = rowSum;
    }

    public void setCellSum(int cellSum) {
        CellSum = cellSum;
    }

    public XSSFWorkbook getWb() {
         return wb;
     }

     public XSSFSheet getSheet() {
        return sheet;
    }

    public String getSheetName() {
        return sheetName;
    }

    public int getRowSum() {
        return RowSum;
    }

    public int getCellSum() {
        return CellSum;
    }




    /**
     * @param   filepath 文件路径
     * @return  文件流创建XSSFWorkBook对象
     */

    public  XSSFWorkbook XSSFReadFile(String filepath){

        FileInputStream stream=null;
        XSSFWorkbook wb=null;
        try {
            stream=new FileInputStream(new File(filepath));
            wb=new XSSFWorkbook(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wb;
    }

    /**
     *
     * @param   is  输入流
     * @return  输入流创建XSSFWorkBook对象
     */
     public  XSSFWorkbook XSSFReadFile(InputStream is) {

         XSSFWorkbook wb=null;
         try {
             wb=new XSSFWorkbook(is);
         } catch (IOException e) {
             e.printStackTrace();
         }
          return wb;
     }


    /**
     * @param wb XSSFWorkBook对象
     * @return 读取文件的sheet名与首行Title组装为json对象
     */
    public  JSONObject getTable(XSSFWorkbook wb){

        JSONObject obj=new JSONObject();

        JSONArray array=new JSONArray();

        int SheetSum = wb.getNumberOfSheets();

        for(int i=0;i<SheetSum;i++){

             String sheetName=wb.getSheetName(i);

             XSSFSheet sheet = wb.getSheetAt(i);

            XSSFRow row = sheet.getRow(0);

            if(row!=null) {

                int cellsSum = row.getPhysicalNumberOfCells();
                array.clear();
                for (int j = 0; j < cellsSum; j++) {

                    array.add(row.getCell(j).getStringCellValue().trim());
                }
                obj.put(sheetName,array);
            }
        }

       return obj;

    }

     /**
      * @param   poi 类对象
      * @return  获取sheet页的全部数据
      */

    public  JSONArray getTableData(POIHelper poi){

        JSONObject table = getTable(poi.getWb());

        JSONArray array = JSONArray.fromObject(table.get(poi.getSheetName()));

        JSONArray arr=new JSONArray();

        JSONObject obj_sun =new JSONObject();

        for(int i=1;i<poi.getRowSum();i++){

            XSSFSheet sheet = poi.getSheet();
            XSSFRow row = sheet.getRow(i);
            obj_sun.clear();
            for(int j=0;j<poi.getCellSum();j++){

                XSSFCell cell = row.getCell(j);

                //设置单元格类型
                cell.setCellType(CellType.STRING);


                obj_sun.put(array.get(j),cell.getStringCellValue().trim());
            }
            arr.add(obj_sun);
        }

        return arr;
    }


    public static  void main(String[] args){

        long start=System.currentTimeMillis();

        POIHelper poi=new POIHelper();

        XSSFWorkbook wb = poi.XSSFReadFile("C:\\Users\\caesar\\Desktop\\user.xlsx");

        POIHelper pos=new POIHelper(wb,"User");

        JSONArray table = poi.getTableData(pos);

        System.out.println(table.toString());

        List<User> userList =(List<User>) JSONArray.toCollection(table, User.class);

        for (User news : userList) {
            System.out.println(news);
        }


        System.out.println(System.currentTimeMillis()-start);



    }



}
