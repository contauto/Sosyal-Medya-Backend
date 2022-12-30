package com.example.learningSpring.user;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private long id;


    @NotNull(message="{learningSpring.Username.NotNull.Message}")
    @Size(min = 3,max = 48)
    @UniqueUsername
    @JsonView(Views.Base.class)
    private String username;

    @Size(min = 2,max = 64)
    @NotNull(message="{learningSpring.Name.NotNull.Message}")
    @JsonView(Views.Base.class)
    private String name;

    @Size(min = 8,max = 64)
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",message="{learningSpring.Password.Pattern.Message}")
    @NotNull(message="{learningSpring.Password.NotNull.Message}")
    @JsonView(Views.Sensitive.class)
    private String password;


    @JsonView(Views.Base.class)
    private String image;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("Role_user");
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
