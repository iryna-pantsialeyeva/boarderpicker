package info.freeit.boarderpicker.dto;

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
public class BPUserDetails implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private boolean isActive;
    private Collection<? extends GrantedAuthority> authorities;


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
        return isActive;
    }

    private static Collection<? extends GrantedAuthority> fromRoles(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).toList();
    }

    public static BPUserDetails getUserDetails(User user) {
        return BPUserDetails.builder()
                .id(user.getId())
                .username(user.getUserName())
                .password(user.getPassword())
                .isActive(user.isActive())
                .authorities(fromRoles(user.getRoles()))
                .build();
    }
}
