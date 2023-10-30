package com.store.managementapplication.services;

import com.store.managementapplication.entities.Role;
import com.store.managementapplication.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // Create a new Role
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    // Update an existing Role
    public Role updateRole(Long id, Role role) throws Exception {
        if (roleRepository.existsById(id)) {
            role.setId(id);
            return roleRepository.save(role);
        } else {
            throw new Exception("Role not found");
        }
    }

    // Delete a Role by its ID
    public void deleteRole(Long id) throws Exception {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
        } else {
            throw new Exception("Role not found");
        }
    }

    // Get all Roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Get a specific Role by ID
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }
}
