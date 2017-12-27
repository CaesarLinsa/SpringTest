package com.soft.dao;

import com.soft.entity.User;

import java.util.List;

public interface UserDao {
   /**
    *
    * @param usersList 插入用户的list集合
    */
   public void batchInsert(List<User> usersList);

   /**
    * @return 分页查询
    */
   public List<User> selectAll();

   /**
    *
    * @param user  插入一条用户记录
    */
   public void InsertUser(User user);


}
