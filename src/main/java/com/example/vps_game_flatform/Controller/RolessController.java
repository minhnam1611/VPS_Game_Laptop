package com.example.vps_game_flatform.Controller;


import com.example.vps_game_flatform.DAO.system.MenuOfRoleReponsitory;
import com.example.vps_game_flatform.Entity.system.SysMenuOfRole;
import com.example.vps_game_flatform.Entity.system.ReponseBase;
import com.example.vps_game_flatform.Entity.system.ReponseObject;
import com.example.vps_game_flatform.Entity.system.SysRole;
import com.example.vps_game_flatform.Service.system.MenuOfRoleService;
import com.example.vps_game_flatform.Service.system.MenuOfRoleServiceIplm;
import com.example.vps_game_flatform.Service.system.MenuService;
import com.example.vps_game_flatform.Service.system.RolessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RolessController {
    @Autowired
    protected RolessService rolesService;

    @Autowired
    protected MenuOfRoleService menuOfRoleService;

    @Autowired
    protected MenuService menuService;

    @GetMapping("/search-role")
    @PreAuthorize("hasAnyAuthority('READ')")
    public ReponseObject searchRole(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        int totalRole = rolesService.getTotalRoles(code,name);
        int start = (page-1)*pageSize;
        int totalPage = 0;
        if(totalRole%pageSize==0){
            totalPage = totalRole/pageSize;
        }else{
            totalPage = totalRole/pageSize+1;
        }
        if(page > totalPage){
            return new ReponseObject(ReponseObject.Fail,"Trang không tồn tại",totalPage,"");
        }else {
            return new ReponseObject(ReponseObject.SUCCESS,"OK",totalPage,rolesService.getListRoles(code, name,pageSize,start));
        }
    }
    @GetMapping("/list-all-role")
    @PreAuthorize("hasAnyAuthority('READ')")
    public ReponseObject searchRole(){
        List<SysRole> list =  rolesService.getAllRoles();
        return new ReponseObject(ReponseObject.SUCCESS,"OK",1,list);
    }

    @PostMapping("/save-role")
    @PreAuthorize("hasAnyAuthority('INSERT')")
    public ReponseBase upsertRole(@RequestBody SysRole roles){
        rolesService.saveRoles(roles);
        return new ReponseBase(ReponseObject.SUCCESS,"OK");
    }
    @DeleteMapping("/delete-role/{roleId}")
    @PreAuthorize("hasAnyAuthority('DELETE')")
    public ReponseBase deleteRole(@PathVariable int roleId){
        boolean check = rolesService.deteleRoles(roleId);
        if(check){
            return new ReponseBase(ReponseObject.SUCCESS,"OK");
        }
        return new ReponseBase(ReponseObject.Fail,"Vai trò không tồn tại");
    }

    @GetMapping("/menu-of-role/{idr}")
    public List<SysMenuOfRole> getMenuOfRole(@PathVariable Integer idr){
        return menuOfRoleService.getMenuOfRoles(idr);
    }
//    @PostMapping("/save-menu")
//    public ReponseBase saveMenuForRole(@RequestParam String idm,@RequestParam String idr){
//        List<Integer> listMenu = getListID(idm);
//        Integer idRole = Integer.valueOf(idr);
//        for(Integer idMenu: listMenu){
//            menuOfRoleService.saveMenuForRole(idMenu,idRole);
//        }
//        return new ReponseBase(ReponseObject.SUCCESS,"Inserted Menus For Role");
//    }



    public List<Integer> getListID(String s){
        String[] parts = s.split(",");
        List<Integer> rs = new ArrayList<>();
        for(String a: parts){
            rs.add(Integer.valueOf(a));
        }
        return rs;
    }

}
