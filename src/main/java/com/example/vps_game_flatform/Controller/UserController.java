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

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list-user")
    public List<SysUser> getListUser(
            @RequestParam(defaultValue = "a") String loginName,
            @RequestParam(defaultValue = "a") String fullName,
            @RequestParam(defaultValue = "a") String email,
            @RequestParam(defaultValue = "a") String msisdn,
            @RequestParam(defaultValue = "10") int page,
            @RequestParam(defaultValue = "1") int pageSize
    ){
        return userService.getListUser(loginName,fullName,email,msisdn,page,pageSize);

    }
}
