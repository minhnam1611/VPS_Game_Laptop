package com.example.vps_game_flatform.Service;

import com.example.vps_game_flatform.Entity.SysUser;

import java.util.List;

public interface UserService {

    public List<SysUser> getListUser(String loginName,String fulllName, String email, String msisdn,int page, int pageSize);
}
