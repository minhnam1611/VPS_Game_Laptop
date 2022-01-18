package com.example.vps_game_flatform.Controller;

import com.example.vps_game_flatform.DAO.UserRepository;
import com.example.vps_game_flatform.Entity.ReponseObject;
import com.example.vps_game_flatform.Entity.SysUser;
import com.example.vps_game_flatform.Service.UserService;
import com.example.vps_game_flatform.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/list-user")
    public ReponseObject getListUser(
            @RequestParam(required = false) String loginName,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String msisdn,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ){

        int totalUsers = userService.getTotalUsers(loginName,fullName,email,msisdn);
        int totalPage =0;
        if(totalUsers%pageSize==0){
            totalPage = totalUsers/pageSize;
        }else{
            totalPage = totalUsers/pageSize+1;
        }
        int start = (page-1)*pageSize;
        if(page > totalPage){
            return new ReponseObject(ReponseObject.Fail,"Bab Request: Trang không tồn tại ",totalPage,"");
        }
        List<SysUser> list = userService.getListUser(loginName,fullName,email,msisdn,pageSize,start);
        return new ReponseObject(ReponseObject.SUCCESS,"SUCCESS",totalPage,list);
    }
    @GetMapping("/search-user")
    public ReponseObject searchUser(@RequestParam(required = false) String keyWord){
        List<SysUser> list = userService.searchUser(keyWord);
        if(list.size()==0) {
            return new ReponseObject(ReponseObject.SUCCESS, "Không tìm thấy ", 1, "");
        }
        return new ReponseObject(ReponseObject.SUCCESS,"OK",1,list);
    }

}
