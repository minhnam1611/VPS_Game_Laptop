package com.example.vps_game_flatform.security;

import com.example.vps_game_flatform.Entity.account.Tokens;
import com.example.vps_game_flatform.Service.account.TokensService;
import com.example.vps_game_flatform.Service.account.UserAccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokensService verificationTokenService;

    @Autowired
    private UserAccService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        UserPrincipal user = null;
        Tokens token = null;

        //kiểm tra Key và Value trên Headers đã đúng định dạng ?
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Token ")) {
            // lấy chuỗi token từ Headers
            String jwt = authorizationHeader.substring(6);
            // lấy thông tin user từ token
            user = jwtUtil.getUserFromToken(jwt);
            //lấy thông tin token bằng Accsess token
            token = verificationTokenService.findByToken(jwt.trim());
        }
        //Trường hợp Acctoken đã hết hạn
        if(null != token && token.getTokenExpDate().before(new Date())){
            System.out.println("AccessToken đã hết hạn.");
            System.out.println("Quá trình Refresh Token .......");
            //lấy id user từ refresh token
            Long iduser = verificationTokenService.findUserByRefreshtoken(token.getRefreshtoken());
            //lấy tên user từ id
            String userrf = userService.findUsernameFromId(iduser);
            //lấy usrePrincipal từ username
            UserPrincipal userPrincipal = userService.findByUsername(userrf);
            //tạo cặp token mới
            Tokens newToken = new Tokens();
            newToken.setIduser(iduser);
            newToken.setToken(jwtUtil.generateToken(userPrincipal));
            newToken.setRefreshtoken(jwtUtil.generateRfToken(userPrincipal));
            newToken.setTokenExpDate(jwtUtil.generateExpirationDate());
            newToken.setRfTokenExpDate(jwtUtil.generateExpirationDaterf());
            System.out.println("New AccessToken: "+ newToken.getToken());
            System.out.println("New RefreshToken: "+ newToken.getRefreshtoken());
            verificationTokenService.createToken(newToken);
        }
        //Kiểm tra: user và token khác null && token chưa hết hạn ====> trả về các quyền truy cập
        if (null != user && null != token && token.getTokenExpDate().after(new Date())) {
            //tạo 1 HashSet gồm các quyền
            Set<GrantedAuthority> authorities = new HashSet<>();
            //tạo quyền cho user
            user.getAuthorities().forEach(p -> authorities.add(new SimpleGrantedAuthority((String) p)));
            //tạo User-Quyền: Thông tin User + quyền truy cập
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
