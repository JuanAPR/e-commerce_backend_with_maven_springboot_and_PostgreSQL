package com.prosigmaka.backendjavafinal.entity.auth;

import com.prosigmaka.backendjavafinal.helper.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userlogin", schema = "public", catalog = "POS")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "first_name",nullable = false)
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @Pattern(regexp = "^[a-zA-Z]*$",message = "can only put alphabetical character")
    @Size(max = 255, message = "check first name validation")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @Pattern(regexp = "^[a-zA-Z]*$",message = "can only put alphabetical character")
    @Size(max = 255, message = "check last name validation")
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    //@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "check email validation")
    @Pattern(regexp = "^[a-zA-Z0-9@._+-]*$",message = "can only put alphanumeric character, @, ., _, +, -")
    @Size(max = 255, message = "check email size validation")
    private String email;
    @Column(name = "password", nullable = false)
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    private String password;
    @Column(name = "create_date")
    private Timestamp date;

    @Enumerated
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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
        return true;
    }
}
