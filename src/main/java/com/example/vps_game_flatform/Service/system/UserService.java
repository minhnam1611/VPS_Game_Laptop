package com.example.vps_game_flatform.Service.system;

import com.example.vps_game_flatform.Entity.system.SysUser;

import java.util.List;

public interface UserService {

    Integer getTotalUsers(String loginName,String fullName, String email, String msisdn);
    List<SysUser> getListUser(String loginName,String fullName, String email, String msisdn,int page, int pageSize);
    List<SysUser> searchUser(String keyword);
}
