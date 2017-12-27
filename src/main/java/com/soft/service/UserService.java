package com.soft.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soft.dao.UserDao;
import com.soft.datasource.DataSourceContextHolder;
import com.soft.entity.User;
import com.soft.util.DataBase;
import com.soft.util.DataSourceType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserDao userDao;


    @Cacheable(value = "demo")
    @DataBase(value = DataSourceType.DateBase_master)
    public List<User> selectUserByPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userDao.selectAll();
        System.out.println("为数据做了缓存");
        return users;


    }

    @DataBase(value = DataSourceType.DateBase_Slave)
    public void insertUser(User user) {
        userDao.InsertUser(user);
    }





}
