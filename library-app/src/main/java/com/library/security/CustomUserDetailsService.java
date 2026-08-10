package com.library.security;

import com.library.application.repository.UserRepository;
import com.library.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//Spring Security'nin kullanıcı doğrulama mekanizması ile veritabanındaki User tablosu arasında köprü kuran sınıf
@Service //İş kurallarının çalıştığı yer
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // E-posta ile kullanıcıyı veri tabanından arar ve kullanıcı varsa UserDetails nesnesini döndürür
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + email);
        }
        //User entitysini Spring Security'nin UserDetails nesnesine dönüştürür
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getRole() != null ? user.getRole().name() : "ROLE_USER")
                .build();
    }
}
