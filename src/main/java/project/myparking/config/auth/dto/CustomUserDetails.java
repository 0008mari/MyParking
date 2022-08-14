package project.myparking.config.auth.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.myparking.domain.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1256711395932122675L;
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

//        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                .collect(Collectors.toList());
        Set<String> s = new HashSet<>();
        s.add(user.getRoleKey());

        // Spring Security 의 SimpleGrantedAuthority 가 String 을 받기 때문에 무조건 맞춰서 넣어줘야함
        return s.stream().map(roleKey -> new SimpleGrantedAuthority(roleKey))
                .collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
//    private String username;
//    private String password;
//    private boolean isEnabled;
//    private boolean isAccountNonExpired;
//    private boolean isAccountNonLocked;
//    private boolean isCredentialsNonExpired;
//    private Collection<? extends GrantedAuthority> authorities;
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }
//    @Override
//    public String getPassword() {
//        return password;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }
//    @Override
//    public boolean isEnabled() {
//        return isEnabled;
//    }
//    public void setEnabled(boolean isEnabled) {
//        this.isEnabled = isEnabled;
//    }
//    @Override
//    public boolean isAccountNonExpired() {
//        return isAccountNonExpired;
//    }
//    public void setAccountNonExpired(boolean isAccountNonExpired) {
//        this.isAccountNonExpired = isAccountNonExpired;
//    }
//    @Override
//    public boolean isAccountNonLocked() {
//        return isAccountNonLocked;
//    }
//    public void setAccountNonLocked(boolean isAccountNonLocked) {
//        this.isAccountNonLocked = isAccountNonLocked;
//    }
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return isCredentialsNonExpired;
//    }
//    public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
//        this.isCredentialsNonExpired = isCredentialsNonExpired;
//    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
//        this.authorities = authorities;
//    }
}
