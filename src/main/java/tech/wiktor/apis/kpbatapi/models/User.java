package tech.wiktor.apis.kpbatapi.models;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tech.wiktor.apis.kpbatapi.enums.RoleEnum;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity(name = "users")
@Getter
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    private String name;

    public User(String email, String password, RoleEnum role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}