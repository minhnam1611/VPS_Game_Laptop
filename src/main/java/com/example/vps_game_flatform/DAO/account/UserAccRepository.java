package com.example.vps_game_flatform.DAO.account;


import com.example.vps_game_flatform.Entity.account.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccRepository extends JpaRepository<Users,Long> {
    Users findByUsername(String username);
}
