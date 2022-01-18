package com.example.vps_game_flatform.Service;

import com.example.vps_game_flatform.Entity.SysUser;

import java.util.List;

public interface UserService {

    Integer getTotalUsers(String loginName,String fullName, String email, String msisdn);
    List<SysUser> getListUser(String loginName,String fullName, String email, String msisdn,int page, int pageSize);
    List<SysUser> searchUser(String keyword);
}
