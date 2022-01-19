package com.example.vps_game_flatform.Service.system;

import com.example.vps_game_flatform.DAO.system.MenuRepository;
import com.example.vps_game_flatform.Entity.system.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceIplm implements  MenuService{
    @Autowired
    private MenuRepository repository;

    @Override
    public List<SysMenu> getTotalMenu(String code, String name, String url) {
        return repository.getMenu(code,name,url);
    }

    @Override
    public List<SysMenu> getMenuPagi(String code, String name, String url, int limit, int start) {
        return repository.getMenuPagi(code,name,url,limit,start);
    }

    @Override
    public boolean insertMenu(SysMenu newMenu) {
        SysMenu oldMenu = repository.getBySys_menu_id(newMenu.getSys_menu_id());
        if(oldMenu==null){

            repository.save(newMenu);

            return true;

        }else{
            oldMenu.setCode(newMenu.getCode());
            oldMenu.setName(newMenu.getName());
            oldMenu.setParent_id(newMenu.getParent_id());
            oldMenu.setSort_order(newMenu.getSort_order());
            oldMenu.setUrl(newMenu.getUrl());
            repository.save(oldMenu);
            return false;
        }
    }

    @Override
    public boolean deleteMenu(int idm) {
        SysMenu oldMenu = repository.getBySys_menu_id(idm);
        if(oldMenu == null){
            return  false;
        }else {
            repository.delete(oldMenu);
            return true;
        }
    }
}
