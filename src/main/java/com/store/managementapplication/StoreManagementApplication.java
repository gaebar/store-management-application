package com.store.managementapplication;

import com.store.managementapplication.entities.Role;
import com.store.managementapplication.entities.User;
import com.store.managementapplication.repositories.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class StoreManagementApplication implements ApplicationRunner {

    // Repositories and PasswordEncoder are injected via constructor
    private final UserRepository userRepository;

    // Constructor-based dependency injection
    public StoreManagementApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    }
}
