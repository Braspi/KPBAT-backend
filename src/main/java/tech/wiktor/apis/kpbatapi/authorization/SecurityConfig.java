package tech.wiktor.apis.kpbatapi.authorization;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import tech.wiktor.apis.kpbatapi.enums.RoleEnum;
import tech.wiktor.apis.kpbatapi.models.User;
import tech.wiktor.apis.kpbatapi.repositories.UserRepository;

@Configuration
public class SecurityConfig {

    private final UserRepository userRepo;
    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfig(UserRepository userRepo, JwtTokenFilter jwtTokenFilter) {
        this.userRepo = userRepo;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    // Add 2 users at start
    @EventListener(ApplicationReadyEvent.class)
    public void saveUser() {
//        User user1 = new User("test1@gmail.com", getBcryptPasswordEncoder().encode("admin123"), RoleEnum.ROLE_USER);
//        userRepo.save(user1);
//        User user2 = new User("test2@gmail.com", getBcryptPasswordEncoder().encode("admin123"), RoleEnum.ROLE_ADMIN);
//        userRepo.save(user2);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email not found: " + username));
    }

    @Bean
    public PasswordEncoder getBcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authorizationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
        http.authorizeRequests()
                .antMatchers("/api/auth/login").permitAll()
                .antMatchers("/api/auth/me").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/messages/send").permitAll()
                .antMatchers("/api/messages/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/hello").hasRole("ADMIN")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}