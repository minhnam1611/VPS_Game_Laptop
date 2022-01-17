package com.example.vps_game_flatform.DAO;

import com.example.vps_game_flatform.Entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<SysUser, Integer> {
    // ?1
    @Query(value = "SELECT * FROM sys_user " +
            "WHERE (?1 = 'a' OR ?1 = login_name ) " +
            "AND (?2 ='a' OR ?2 = full_name )  " +
            "AND (?3 ='a' OR ?3 = email  ) " +
            "AND (?4 ='a' OR ?4 = phone_number ) " +
            "LIMIT ?5 OFFSET ?6",nativeQuery = true)
    List<SysUser> findListUser(String loginName, String fullName, String email, String msisdn, int page, int pageSize);

}
