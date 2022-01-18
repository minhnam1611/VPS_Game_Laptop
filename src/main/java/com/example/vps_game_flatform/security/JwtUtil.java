package com.example.vps_game_flatform.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class  JwtUtil {
    private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    private static final String USER = "user";
    private static final String SECRET = "anhdangminhnamthatladeptraiquadiahihihihihihihihihihihihihihihihihihi";
    //Tạo access Token
    public String generateToken(UserPrincipal user){
        String token = null;
        try{
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim(USER, user);
            builder.expirationTime(generateExpirationDate());
            JWTClaimsSet claimsSet = builder.build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256),claimsSet);
            JWSSigner signer =  new MACSigner(SECRET.getBytes());
            signedJWT.sign(signer);
            token = signedJWT.serialize();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return token;
    }
    //Tạo access Token
    public String generateRfToken(UserPrincipal user){
        String token = null;
        try{
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim(USER, user);
            builder.expirationTime(generateExpirationDaterf());
            JWTClaimsSet claimsSet = builder.build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256),claimsSet);
            JWSSigner signer =  new MACSigner(SECRET.getBytes());
            signedJWT.sign(signer);
            token = signedJWT.serialize();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return token;
    }
    //tạo time hết hạn Access token (10 phút)
    public Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + 1000*60*10);
    }
    //tạo time hết hạn của Refresh Token(3 ngày)
    public Date generateExpirationDaterf() {
        return new Date(System.currentTimeMillis() + 1000*60*60*24*3);
    }
    //lấy dữ liệu Payload
    private JWTClaimsSet getClaimsFromToken(String token){
        JWTClaimsSet claims = null;
        try{
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET.getBytes());
            if(signedJWT.verify(verifier)){
                claims = signedJWT.getJWTClaimsSet();
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return claims;
    }
    //lấy time hết hạn
    private Date getExpirationDateFromToken(JWTClaimsSet claims) {
        return claims != null ? claims.getExpirationTime() : new Date();
    }
    //Kiểm tra token hết hạn chưa ?
    private boolean isTokenExpired(JWTClaimsSet claims) {
        return getExpirationDateFromToken(claims).after(new Date());
    }
    public UserPrincipal getUserFromToken(String token) {
        UserPrincipal user = null;
        try {
            JWTClaimsSet claims = getClaimsFromToken(token);
            if (claims != null && isTokenExpired(claims)) {
                JSONObject jsonObject = (JSONObject) claims.getClaim(USER);
                user = new ObjectMapper().readValue(jsonObject.toJSONString(), UserPrincipal.class);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return user;
    }

}
