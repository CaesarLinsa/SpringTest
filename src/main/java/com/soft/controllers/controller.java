package com.soft.controllers;


import com.soft.entity.User;
import com.soft.service.FileDataBatchOperation;
import com.soft.service.PushService;
import com.soft.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class controller {

    @Autowired
    private PushService pushService;

    @Autowired
    private FileDataBatchOperation fb;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/say",method = RequestMethod.GET)
    public String say(HttpServletRequest request){
        return "sucess";
    }

//    @RequestMapping(value = "upload",method=RequestMethod.POST)
//    @ResponseBody
//    public String upload(MultipartFile file){
//
//     File f=new File("d://logs//"+file.getOriginalFilename());
//
//
//        System.out.println();
//
//        try {
//            file.transferTo(f);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return  "ok";
//    }

    @RequestMapping(value = "/defer")
    @ResponseBody
   public DeferredResult<String> deferredCall(){

       return pushService.getAsynDeferredResult();

   }


    @RequestMapping(value = "/upload",method=RequestMethod.POST)
    @ResponseBody
    public String Upload_Data(MultipartFile file){

    fb.BatchInsertData(file);

     return "ok";
   }

    @RequestMapping(value = "/findUser",method=RequestMethod.GET)
    @ResponseBody
   public String selectUserByPage(int pageNum,int pageSize){

        List<User> users = userService.selectUserByPage(pageNum, pageSize);

        JSONObject obj=new JSONObject();
        for (int i=0;i< users.size();i++) {

            obj.put(i,users.get(i).toString());

        }
       System.out.println(obj.toString());
        return obj.toString();
   }


    @RequestMapping(value="/addUser",method = RequestMethod.GET)
    @ResponseBody
    public String addUser(User user){

        userService.insertUser(user);

        return  "addUser===>ok";

    }


}
