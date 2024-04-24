package com.aztu.job_application.model.entity;

import com.aztu.job_application.model.entity.userInformation.UserInformation;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    String surname;

    @Column(unique = true)
    String email;

    String password;

    @OneToOne(cascade = CascadeType.PERSIST)
    TableDetail tableDetail;

    @OneToOne(cascade = CascadeType.PERSIST)
    UserInformation userInformation;

    @ManyToOne
    Role role;

    @Builder.Default
    boolean isEnable = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> permissions = getAllPermissions();
        permissions.add(new Permission("ROLE_" + role.getName()));
        return permissions;
    }

    private List<GrantedAuthority> getAllPermissions() {
        return role.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getAuthority()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return isEnable;
    }
}
