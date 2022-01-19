package com.example.vps_game_flatform.Controller;

import com.example.vps_game_flatform.Entity.system.ReponseBase;
import com.example.vps_game_flatform.Entity.system.ReponseObject;
import com.example.vps_game_flatform.Entity.system.SysRole;
import com.example.vps_game_flatform.Service.system.RolessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RolessController {
    @Autowired
    protected RolessService rolesService;

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

}
