package com.example.vps_game_flatform.Service.system;

import com.example.vps_game_flatform.Entity.system.SysRole;

import java.util.List;

public interface RolessService {
    Integer getTotalRoles(String code,String name);
    List<SysRole> getListRoles(String code, String name, int limit , int start);
    List<SysRole> getAllRoles();
    void saveRoles(SysRole newRoles);

    boolean deteleRoles(int roleId);
}
