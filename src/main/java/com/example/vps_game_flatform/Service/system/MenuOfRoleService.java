package com.example.vps_game_flatform.Service.system;
import com.example.vps_game_flatform.Entity.system.SysMenuOfRole;

import java.util.List;

public interface MenuOfRoleService {
    List<SysMenuOfRole> getMenuOfRoles(int roleId);
    void saveMenuForRole(Integer idMenu,Integer idRole);
}
