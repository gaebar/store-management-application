package com.store.managementapplication;

import com.store.managementapplication.entities.Role;
import com.store.managementapplication.entities.User;
import com.store.managementapplication.repositories.RoleRepository;
import com.store.managementapplication.repositories.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class StoreManagementApplication implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public StoreManagementApplication(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    /**
     * Main method to start the Spring Boot application.
     *
     * @param args command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(StoreManagementApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role role = new Role();
        role.setName("ADMIN");
        roleRepository.save(role);

        // load initial data in test DB
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        userRepository.save(user);

        user.addRole(role);
        userRepository.save(user);
    }
}

