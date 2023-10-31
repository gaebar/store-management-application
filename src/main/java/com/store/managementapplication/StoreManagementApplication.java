package com.store.managementapplication;

import com.store.managementapplication.entities.Role;
import com.store.managementapplication.entities.User;
import com.store.managementapplication.repositories.RoleRepository;
import com.store.managementapplication.repositories.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class StoreManagementApplication implements ApplicationRunner {

    // Repositories and PasswordEncoder are injected via constructor
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor-based dependency injection
    public StoreManagementApplication(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(StoreManagementApplication.class, args);
    }

    // The run method that Spring Boot calls when the application starts
    @Override
    @Transactional  // Ensure this method runs in a transactional context
    public void run(ApplicationArguments args) {
        // Create a new Role object and save it
        Role role = new Role();
        role.setName("ADMIN");
        role = roleRepository.save(role);

        // Create a new User object
        User user = new User();

        // Using setEmail since setUsername doesn't exist in User class
        user.setEmail("admin@example.com");

        // Use PasswordEncoder to encode the user's password before saving
        user.setPassword(passwordEncoder.encode("admin"));

        // Add the ADMIN role to the user
        user.addRole(role);

        // Save the User object, which now has an encoded password and a role
        userRepository.save(user);
    }
}
