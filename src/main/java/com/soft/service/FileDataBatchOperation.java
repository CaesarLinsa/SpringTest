package com.soft.service;

import com.soft.dao.ProductDao;
import com.soft.dao.UserDao;
import com.soft.entity.User;
import com.soft.util.EntityMapper;
import com.soft.util.POIHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class FileDataBatchOperation {

    @Resource
    private POIHelper poiHelper;
    @Resource
    private EntityMapper entitymapper;
    @Resource
    private ProductDao productDao;
    @Resource
    private UserDao userDao;

    public FileDataBatchOperation() {
    }

    public void BatchInsertData(MultipartFile file){
        try {
            XSSFWorkbook wb = poiHelper.XSSFReadFile(file.getInputStream());
            JSONObject table = poiHelper.getTable(wb);
            Set<String> set = table.keySet();
            for (String sheetName:set) {
                POIHelper poi=new POIHelper(wb,sheetName);
                JSONArray tableData = poi.getTableData(poi);
                Class<?> clazz = entitymapper.getClazz(sheetName);
                List list =(List) JSONArray.toCollection(tableData,clazz);
                if(sheetName.equals("User")){
                    userDao.batchInsert(list);
                }else if(sheetName.equals("Product")){
                    productDao.batchInsert(list);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
