package com.store.managementapplication.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

/**
 * Represents a user role in the store management application.
 * Each role has an id and a name which denotes the permissions and responsibilities.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * Sets the id for the role.
     *
     * @param id the new id for the role.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the name for the role.
     *
     * @param name the new name for the role.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
