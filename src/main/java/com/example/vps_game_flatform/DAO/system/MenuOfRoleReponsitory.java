package com.example.vps_game_flatform.DAO.system;

import com.example.vps_game_flatform.Entity.system.SysMenuOfRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuOfRoleReponsitory extends JpaRepository<SysMenuOfRole,Integer> {
    @Query(value = "SELECT * FROM roles_menus WHERE sys_role_id = ?1",nativeQuery = true)
    List<SysMenuOfRole> getMenuByRoleID(int roleId);
    @Query(value = "INSERT INTO role_menu (sys_menu_id , sys_role_id , is_active) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void saveMenuForRole(int idMenu,int idRole , int isActive);
}
