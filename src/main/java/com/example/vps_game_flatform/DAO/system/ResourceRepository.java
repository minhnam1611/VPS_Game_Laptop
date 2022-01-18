package com.example.vps_game_flatform.DAO.system;

import com.example.vps_game_flatform.Entity.system.SysResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ResourceRepository extends JpaRepository<SysResource,Integer> {
    @Query("SELECT rs FROM SysResource rs WHERE rs.sys_resource_id = ?1")
    SysResource getBySys_resource_id(int id);
    List<SysResource> findByCode(String code);
    List<SysResource> findByName(String name);

    @Query(value = "SELECT * FROM sys_resource  LIMIT ?1 OFFSET ?2",nativeQuery = true)
    List<SysResource> findAllPage(int limit,int start);

    @Query(value = "SELECT * FROM sys_resource  WHERE code =?1 AND name = ?2",nativeQuery = true)
    List<SysResource> findAllPage1(String code,String name );

    @Query(value = "SELECT * FROM sys_resource WHERE code =?1  LIMIT ?2 OFFSET ?3",nativeQuery = true)
    List<SysResource> findAllPage2(String code ,int limit,int start);

    @Query(value = "SELECT * FROM sys_resource WHERE name =?1  LIMIT ?2 OFFSET ?3",nativeQuery = true)
    List<SysResource> findAllPage3(String name ,int limit,int start);

    @Query(value = "SELECT * FROM sys_resource WHERE code =?1 AND name =?2 LIMIT ?3 OFFSET ?4",nativeQuery = true)
    List<SysResource> findAllPage4(String code, String name ,int limit,int start);
}
