package com.store.managementapplication.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a user in the store management application.
 * Each user has an id, username, password, and associated stores.
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

    // Personal information fields
    private String firstname;
    private String lastname;

    @Column(unique = true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role.RoleType role;

    // Getter and Setter for managedStores
    @Getter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "users_managed_stores",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "store_id"))
    @Builder.Default
    private Set<Store> managedStores = new HashSet<>();  // Initialize to avoid null

    // Method to add a role to the user
    public void addManagedStore(Store store) {
        this.managedStores.add(store);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert roles to a list of SimpleGrantedAuthorities
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    // Other UserDetails methods
    @Override
    public String getPassword() {
        return password;
    }

    public void setManagedStores(Set<Store> managedStores) {
        this.managedStores = managedStores;
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
