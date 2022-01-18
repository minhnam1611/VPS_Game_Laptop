package com.example.vps_game_flatform.Service;

import com.example.vps_game_flatform.DAO.UserRepository;
import com.example.vps_game_flatform.Entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository Repo_user;


    @Override
    public Integer getTotalUsers(String loginName, String fullName, String email, String msisdn) {
        return Repo_user.getTotalUsers(loginName,fullName,email,msisdn).size();
    }

    @Override
    public List<SysUser> getListUser(String loginName,String fullName, String email, String msisdn, int limit, int start) {
        return Repo_user.findListUserPagi(loginName,fullName,email,msisdn,limit,start);
    }

    @Override
    public List<SysUser> searchUser(String keyword) {
        return Repo_user.searchUser(keyword);
    }
}
