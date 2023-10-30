package com.store.managementapplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a user in the store management application.
 * Each user has an id, username, password, and associated roles.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role.RoleEnum role;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    /**
     * Sets the id for the user.
     *
     * @param id the new id for the user.
     */
    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(Role role) {
        if (this.roles == null) {
            this.roles = Set.of(role);
            return;
        }
        this.roles.add(role);
    }

    public Set<Role> getRoles() {
        if (this.roles == null) {
            this.roles = new HashSet<Role>();
            return this.roles;
        }
        return roles;
    }

    /**
     * Sets the roles for the user.
     *
     * @param roles the new set of roles for the user.
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        if (this.roles == null) {
            this.roles = new HashSet<Role>();
        }
        this.roles.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Sets the username for the user.
     *
     * @param email the new email for the user.
     */
    public void setUsername(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the user.
     *
     * @param password the new password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
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