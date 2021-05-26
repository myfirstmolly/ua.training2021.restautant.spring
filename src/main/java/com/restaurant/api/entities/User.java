package com.restaurant.api.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"requests", "approvedRequests"})
@Builder
@Table(name = "user")
public final class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 16)
    @NotNull(message = "{username.notnull}")
    @Size(min = 4, max = 16, message = "{username.length}")
    @Pattern(regexp = "[A-Za-z_]+", message = "{username.characters}")
    private String username;

    @Column(nullable = false, length = 64)
    @NotNull(message = "{password.notnull}")
    @Size(min = 6, message = "{password.length}")
    private String password;

    @Column(nullable = false, length = 32)
    @NotNull(message = "{user.name.notnull}")
    @Size(min = 3, max = 32, message = "{user.name.length}")
    @Pattern(regexp = "\\p{Lu}\\p{L}", message = "{user.name.characters}")
    private String name;

    @Column(nullable = false, length = 14)
    @NotNull(message = "{phone.notnull}")
    @Pattern(regexp = "\\([0-9]{3}\\)[0-9]{3}-[0-9]{2}-[0-9]{2}",
            message = "{phone.characters}")
    private String phoneNumber;

    @Column(length = 320)
    @Email(message = "{email.message}")
    private String email;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Request> requests;

    @OneToMany(mappedBy = "approvedBy", fetch = FetchType.LAZY)
    private List<Request> approvedRequests;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
