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
            "WHERE (?1 IS NULL OR login_name = ?1) " +
            "AND (?2 IS NULL OR full_name = ?2) " +
            "AND (?3 IS NULL OR email = ?3 ) " +
            "AND (?4 IS NULL OR phone_number = ?4) " +
            "LIMIT ?5 OFFSET ?6",nativeQuery = true)
    List<SysUser> findListUserPagi(String loginName, String fullName, String email, String msisdn, int limit, int start);

    @Query(value = "SELECT * FROM sys_user " +
            "WHERE (?1 IS NULL OR login_name = ?1) " +
            "AND (?2 IS NULL OR full_name = ?2) " +
            "AND (?3 IS NULL OR email = ?3 ) " +
            "AND (?4 IS NULL OR phone_number = ?4) " ,nativeQuery = true)
    List<SysUser> getTotalUsers(String loginName, String fullName, String email, String msisdn);
}
