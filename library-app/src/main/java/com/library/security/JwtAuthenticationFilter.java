package com.library.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component //Sınıfın Spring tarafından yönetildiğini belirler
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }
    //Gelen her isteği Controller'a ulaşmadan önce yakalar ve isteğin içindeki token'ı kontrol ettirir, geçerliyse kullanıcı e-postasını çıkartır ve isteğe yol verir
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String email = jwtUtils.getEmailFromJwtToken(jwt);
            }
        }catch (Exception e) {
            System.out.println("User verification failed:" + e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
    //Gelen istek başlığındaki "Bearer " kısmını kesip atar geriye sadece işimize yarayan saf JWT token'ı döndürür.
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}
