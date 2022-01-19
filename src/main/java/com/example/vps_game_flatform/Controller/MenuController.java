package com.example.vps_game_flatform.Controller;

import com.example.vps_game_flatform.Entity.system.ReponseObject;
import com.example.vps_game_flatform.Entity.system.SysMenu;
import com.example.vps_game_flatform.Entity.system.SysRole;
import com.example.vps_game_flatform.Service.system.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/list-menu")
    @PreAuthorize("hasAnyAuthority('READ')")
    public ReponseObject searchRole(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String url,
            @RequestParam(required = false) Integer type,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        if (type == null) {
            return new ReponseObject(ReponseObject.Fail, "<Type Không xác định Bab Request>", 0, "");
        } else {
            if (type == 1) {
                return new ReponseObject(ReponseObject.SUCCESS, "<Load All> OK", 1, menuService.getTotalMenu(code, name, url));
            }
            if (type == 2) {
                int totalMenu = menuService.getTotalMenu(code, name, url).size();
                int start = (page - 1) * pageSize;
                int totalPage = 0;
                if (totalMenu % pageSize == 0) {
                    totalPage = totalMenu / pageSize;
                } else {
                    totalPage = totalMenu / pageSize + 1;
                }
                if (page > totalPage) {
                    return new ReponseObject(ReponseObject.Fail, "<Load phân trang> Trang không tồn tại", totalPage, "");
                } else {
                    return new ReponseObject(ReponseObject.SUCCESS, "<Load phân trang> OK", totalPage, menuService.getMenuPagi(code, name, url, totalPage, start));
                }
            }
            return new ReponseObject(ReponseObject.Fail, "<Type Không xác định Bab Request>", 0, "");
        }
    }
    @PostMapping("/save-menu")
    public ReponseObject upsertMenu(
            @RequestBody SysMenu newMenu
    ){
        return null;
    }

}
