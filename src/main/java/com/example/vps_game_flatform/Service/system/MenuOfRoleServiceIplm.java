package com.example.vps_game_flatform.Service.system;

import com.example.vps_game_flatform.DAO.system.MenuOfRoleReponsitory;
import com.example.vps_game_flatform.Entity.system.SysMenuOfRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuOfRoleServiceIplm implements MenuOfRoleService{

    @Autowired
    private MenuOfRoleReponsitory menuOfRoleReponsitory;

    @Override
    public List<SysMenuOfRole> getMenuOfRoles(int roleId) {
        return menuOfRoleReponsitory.getMenuByRoleID(roleId);
    }

    @Override
    public void saveMenuForRole(Integer idMenu,Integer idRole) {
        int isActive =1 ;
        menuOfRoleReponsitory.saveMenuForRole(idMenu,idRole , isActive);
    }
}
