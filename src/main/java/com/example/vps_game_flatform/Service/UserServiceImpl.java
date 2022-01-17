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
    public List<SysUser> getListUser(String loginName,String fullName, String email, String msisdn,int page, int pageSize) {
        return Repo_user.findListUser(loginName,fullName,email,msisdn,page,pageSize);
    }
}
