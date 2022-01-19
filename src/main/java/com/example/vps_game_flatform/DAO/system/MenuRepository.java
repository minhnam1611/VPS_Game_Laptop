package com.example.vps_game_flatform.DAO.system;

import com.example.vps_game_flatform.Entity.system.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<SysMenu,Integer> {
    @Query(value = "SELECT * FROM sys_menu " +
            "WHERE ( code = ?1 OR ?1 IS NULL ) " +
            "AND ( name = ?2 OR ?2 IS NULL  ) " +
            "AND ( url =?3 OR ?3 IS NULL)" +
            "LIMIT ?4 OFFSET  ?5",nativeQuery = true)
    List<SysMenu> getMenuPagi(String code, String name, String url, int limit, int start);

    @Query(value = "SELECT * FROM sys_menu " +
            "WHERE ( code = ?1 OR ?1 IS NULL ) " +
            "AND ( name = ?2 OR ?2 IS NULL  ) " +
            "AND ( url =?3 OR ?3 IS NULL)",nativeQuery = true)
    List<SysMenu>getMenu(String code, String name, String url);
}
