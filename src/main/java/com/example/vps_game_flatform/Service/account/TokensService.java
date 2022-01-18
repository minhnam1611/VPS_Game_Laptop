package com.example.vps_game_flatform.Service.account;


import com.example.vps_game_flatform.Entity.account.Tokens;


public interface TokensService {
    Tokens createToken(Tokens token);

    Tokens findByToken(String token);

    Long  findUserByRefreshtoken(String refreshtoken);

}
