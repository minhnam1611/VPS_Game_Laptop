package com.example.vps_game_flatform.Controller;

import com.example.vps_game_flatform.Entity.account.Tokens;
import com.example.vps_game_flatform.Entity.account.Users;
import com.example.vps_game_flatform.Service.account.TokensService;
import com.example.vps_game_flatform.Service.account.UserAccService;
import com.example.vps_game_flatform.security.JwtUtil;
import com.example.vps_game_flatform.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    UserAccService userService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    TokensService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user){
        UserPrincipal userPrincipal = userService.findByUsername(user.getUsername());
        if (null == userPrincipal || !user.getPassword().equals(userPrincipal.getPassword()) ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tài khoản hoặc mật khẩu không chính xác");
        }
        Tokens token = new Tokens();
        token.setToken(jwtUtil.generateToken(userPrincipal));
        token.setRefreshtoken(jwtUtil.generateRfToken(userPrincipal));
        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        token.setRfTokenExpDate(jwtUtil.generateExpirationDaterf());
        token.setIduser(userPrincipal.getId());
        tokenService.createToken(token);
        return ResponseEntity.ok("Access token: "+token.getToken()+"\nRefresh Token: "+token.getRefreshtoken());
    }
}
