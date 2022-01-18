package com.example.vps_game_flatform.Service.account;



import com.example.vps_game_flatform.DAO.account.UserAccRepository;
import com.example.vps_game_flatform.Entity.account.Users;
import com.example.vps_game_flatform.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserAccServiceImpl implements UserAccService {
    @Autowired
    private UserAccRepository userRepository;

    @Override
    public UserPrincipal findByUsername(String username) {
        Users user = userRepository.findByUsername(username);
        UserPrincipal userPrincipal =new UserPrincipal();
        if(user != null){
            Set<String> authorities =new HashSet<>();
            if(null!=user.getRoles()) user.getRoles().forEach(
                    r ->{
                        authorities.add(r.getName());
                    }
            );
            userPrincipal.setId(user.getId());
            userPrincipal.setUsername(user.getUsername());
            userPrincipal.setPassword(user.getPassword());
            userPrincipal.setAuthorities(authorities);
        }
        return userPrincipal;
    }
    public String findUsernameFromId(long id){
        Users user= userRepository.findById(id).get();
        return user.getUsername();
    }

    @Override
    public Users saveUser(Users users) {
        return userRepository.save(users);
    }

    @Override
    public Users findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserPrincipal> getAllUserPrincipal() {
        List<UserPrincipal> list = new ArrayList<>();
        List<Users> listuser = userRepository.findAll();
        for (Users a :listuser){
            UserPrincipal b = findByUsername(a.getUsername());
            list.add(b);
        }
        return list;
    }
}
