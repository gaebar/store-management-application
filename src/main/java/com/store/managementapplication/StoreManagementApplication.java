package com.store.managementapplication;

import com.store.managementapplication.entities.Role;
import com.store.managementapplication.entities.User;
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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor-based dependency injection
    public StoreManagementApplication(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(StoreManagementApplication.class, args);
    }

    // Helper method to create initial users
    // / private void createInitialUsers() {
    //  //  User new User("admin@example.com", "Molly", "Brown", "adminPassword", Role.RoleType.ADMIN);
    //    new User("manager@example.com", "Tom", "Smith", "managerPassword", Role.RoleType.MANAGER);
    //    new User("staff@example.com", "Jane", "Doe", "staffPassword", Role.RoleType.STAFF);
    // }

    // The run method that Spring Boot calls when the application starts
    @Override
    @Transactional  // Ensure this method runs in a transactional context
    public void run(ApplicationArguments args) {
        User superUser = new User("Molly", "Brown", "admin@example.com", "adminPassword", Role.RoleType.ADMIN);
        userRepository.save(superUser);

        User superUser2 = new User("Tom", "Smith", "manager@example.com", "managerPassword", Role.RoleType.MANAGER);
        userRepository.save(superUser2);

        User superUser3 = new User("Jane", "Doe", "staff@example.com", "staffPassword", Role.RoleType.STAFF);
        userRepository.save(superUser3);
    }
}
