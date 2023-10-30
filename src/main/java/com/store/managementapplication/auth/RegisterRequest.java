package com.store.managementapplication.auth;

import com.store.managementapplication.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private Role.RoleEnum role;
    
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}


