package com.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
//Uygulamanın kimlik doğrulama ve yetkilendirme kurallarının merkezi
@Configuration //Sınıfın konfigurasyon sınıfı olduğunu belirtir
@EnableWebSecurity //Spring Security güvenlik yapılandırmalarını aktif eder
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }
    //Kullanıcı şifrelerini veritabanına kaydetmeden önce BCrypt algoritması ile şifrelemek için kullanılan Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //Kullanıcı giriş işlemlerinde kimlik doğrulamasını yöneten Spring Security bileşeni
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    //Uygulamanın tüm güvenlik filtresi zincirini ve HTTP istek yetkilendirme kurallarını yapılandıran metod
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //CORS ayarlarını aktif eder
                .cors(Customizer.withDefaults())
                //REST API'lerde JWT kullanıldığı için Session tutulmaz. Bu yüzden CSRF korumasını devre dışı bırakır
                .csrf(csrf -> csrf.disable())
                //Sunucuda oturum (session) oluşturulmasını engeller
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //HTTP isteklerinin yetki ve erişim kurallarını tanımlar
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/com/library/api/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    //**'den gelen isteklerin engellenmemesi için CORS kurallarının tanımlandığı metod
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // API'ye erişebilecek kaynak adresleri belirler
        configuration.setAllowedOrigins(List.of("**"));
        //İzin verilen HTTP metodlarını belirler
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        //Tüm HTTP Header'larına (başlık) izin verir
        configuration.setAllowedHeaders(List.of("*"));
        //Kimlik bilgilerinin gönderilmesine izin ver
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
