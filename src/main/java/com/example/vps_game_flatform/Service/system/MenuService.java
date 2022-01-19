package com.example.vps_game_flatform.Service.system;

import com.example.vps_game_flatform.Entity.system.SysMenu;

import java.util.List;

public interface MenuService {
    List<SysMenu> getTotalMenu(String code,String name,String url );
    List<SysMenu> getMenuPagi(String code,String name,String url ,int limit , int start );
    boolean insertMenu(SysMenu newMenu);
    boolean deleteMenu(int idm);
}
