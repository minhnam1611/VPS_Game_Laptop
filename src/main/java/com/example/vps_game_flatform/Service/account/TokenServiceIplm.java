package com.example.vps_game_flatform.Service.account;

import com.example.vps_game_flatform.DAO.account.TokenRepository;
import com.example.vps_game_flatform.Entity.account.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TokenServiceIplm implements TokensService{
    @Autowired
    TokenRepository tokenRepository;
    @Override
    public Tokens createToken(Tokens token) {
        return tokenRepository.saveAndFlush(token);
    }

    @Override
    public Tokens findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public Long findUserByRefreshtoken(String refreshtoken) {
        return tokenRepository.findByRefreshtoken(refreshtoken).getIduser();
    }


}
