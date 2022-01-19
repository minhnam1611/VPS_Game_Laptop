package com.example.vps_game_flatform.DAO.system;

import com.example.vps_game_flatform.Entity.system.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolessRepository extends JpaRepository<SysRole,Integer> {
    @Query(value = "SELECT * FROM sys_role " +
            "WHERE ( code = ?1 OR ?1 IS NULL ) " +
            "AND ( name = ?2 OR ?2 IS NULL  ) ",nativeQuery = true)
    List<SysRole> getTotalRoles(String code, String name);

    @Query(value = "SELECT * FROM sys_role " +
            "WHERE ( code = ?1 OR ?1 IS NULL ) " +
            "AND ( name = ?2 OR ?2 IS NULL  ) " +
            "LIMIT ?3 OFFSET ?4",nativeQuery = true)
    List<SysRole> getListRoles(String code, String name, int page, int pageSize);
    @Query(value = "SELECT * FROM sys_role " +
            "WHERE sys_role_id = ?1",nativeQuery = true)
    SysRole getBySys_role_id(int id);
}
