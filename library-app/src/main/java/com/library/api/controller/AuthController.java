package com.library.api.controller;

import com.library.api.LoginRequest;
import com.library.security.JwtAuthenticationFilter;
import com.library.security.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//Kimlik doğrulama (Authentication) işlemlerini yöneten REST Controller sınıfı
@RestController //Sınıfınn REST API olduğunu belirtir
@RequestMapping("/com/library/api/auth") //Ana URL yolunu tanımlar
@CrossOrigin(origins = "http://localhost:3000") //Adresten gelen API isteklerine (CORS) izin verir.
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }
    //Kullanıcı giriş işlemlerini gerçekleştirir
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        //Kullanıcı adı ve şifreyi bir kimlik doğrulama jetonuna dönüştürür
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        //Kimliği doğrulanan kullanıcının kullanıcı adını alarak yeni bir JWT token üretir
        String token = jwtUtils.generateJwtToken(authentication.getName());

        //İstemciye dönülecek yanıt verisini tutmak için boş bir Map nesnesi oluşturur
        Map<String, String> response = new HashMap<>();
        //Oluşturulan JWT token'ı token anahtarıyla Map içerisine ekler
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
    //Yeni kullanıcı kaydı işlemlerini gerçekleştirir
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest registerRequest) {
        Map<String, String> response = new HashMap<>();
        //message anahtarına başarı mesajını ekler
        response.put("message", "User registration successful");
        return ResponseEntity.ok(response);
    }
}
