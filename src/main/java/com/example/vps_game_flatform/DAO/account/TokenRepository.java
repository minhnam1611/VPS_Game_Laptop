package com.example.vps_game_flatform.DAO.account;


import com.example.vps_game_flatform.Entity.account.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Tokens,Long> {
    Tokens findByToken(String token);

    @Query("SELECT t FROM Tokens t WHERE t.iduser = ?1")
    Optional<Tokens> findById(Long id );

    Tokens findByRefreshtoken(String refreshtoken);
}
