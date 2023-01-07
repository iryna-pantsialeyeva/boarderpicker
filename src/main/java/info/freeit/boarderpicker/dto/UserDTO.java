package info.freeit.boarderpicker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import info.freeit.boarderpicker.entity.Role;
import info.freeit.boarderpicker.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements UserDetails {

    private int id;
    private String username;
    private String email;

    @JsonIgnore
    private String password;
    @JsonIgnore
    private boolean isActive;
    @JsonIgnore
    private Collection<? extends GrantedAuthority> authorities;

    private static Collection<? extends GrantedAuthority> fromRolesToAuthorities (Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).toList();
    }

    public static UserDTO getUserDetails (User user) {
        return UserDTO.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .isActive(user.isActive())
                .authorities(fromRolesToAuthorities(user.getRoles()))
                .build();
    }

    public static UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUserName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return isActive;
    }
}