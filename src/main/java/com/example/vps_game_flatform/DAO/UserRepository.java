package com.example.vps_game_flatform.DAO;

import com.example.vps_game_flatform.Entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<SysUser, Integer> {
    // Start bắt đầu bằng 0
    @Query(value = "SELECT * FROM sys_user " +
            "WHERE ( login_name = ?1 OR ?1 IS NULL ) " +
            "AND ( full_name = ?2 OR ?2 IS NULL  ) " +
            "AND ( email = ?3 OR ?3 IS NULL ) " +
            "AND ( phone_number = ?4 OR ?4 IS NULL) " +
            "LIMIT ?5 OFFSET ?6",nativeQuery = true)
    List<SysUser> findListUserPagi(String loginName, String fullName, String email, String msisdn, int limit, int start);

    @Query(value =  "SELECT * FROM sys_user " +
            "WHERE ( login_name = ?1 OR ?1 IS NULL ) " +
            "AND ( full_name = ?2 OR ?2 IS NULL  ) " +
            "AND ( email = ?3 OR ?3 IS NULL ) " +
            "AND ( phone_number = ?4 OR ?4 IS NULL) "  ,nativeQuery = true)
    List<SysUser> getTotalUsers(String loginName, String fullName, String email, String msisdn);

    @Query(value = "SELECT * FROM sys_user " +
            "WHERE ( login_name LIKE %?1% )" +
            "OR full_name LIKE %?1% " +
            "OR email LIKE %?1% " +
            "OR phone_number  LIKE %?1%" ,nativeQuery = true)
    List<SysUser> searchUser(String keyword);
}
