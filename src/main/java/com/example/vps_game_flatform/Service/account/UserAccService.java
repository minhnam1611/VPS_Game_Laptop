package com.example.vps_game_flatform.Service.account;

import com.example.vps_game_flatform.Entity.account.Users;
import com.example.vps_game_flatform.security.UserPrincipal;

import java.util.List;


public interface UserAccService {

    UserPrincipal findByUsername(String username);
    String findUsernameFromId(long id);

    Users saveUser(Users users);

    Users findUserByUsername(String username);

    List<UserPrincipal> getAllUserPrincipal();
}
