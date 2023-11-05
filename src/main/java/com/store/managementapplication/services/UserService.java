package com.store.managementapplication.services;

import com.store.managementapplication.entities.Store;
import com.store.managementapplication.entities.User;
import com.store.managementapplication.exceptions.GlobalExceptionHandler;
import com.store.managementapplication.exceptions.ResourceNotFoundException;
import com.store.managementapplication.repositories.StoreRepository;
import com.store.managementapplication.repositories.UserRepository;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final SessionFactory sessionFactory;


    public UserService(UserRepository userRepository, StoreRepository storeRepository, SessionFactory sessionFactory) {
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
        this.sessionFactory = sessionFactory;
    }

    public User createUser(User user) {
        String password = user.getPassword();
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) throws ResourceNotFoundException {
        return userRepository.findById(id).map(existingUser -> {
            user.setId(id);
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found"));

    }

    public void deleteUser(Long id) throws ResourceNotFoundException {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Transactional
    public User getUserById(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Hibernate.initialize(user.getManagedStores());
        return user;
    }

    // get user by email
    public User getUserByEmail(String email) throws ResourceNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean isUserStoreManager(String email) {
        return userRepository.findByEmail(email)
                .map(user -> "STORE_MANAGER".equals(user.getRole().name()))
                .orElse(false);
    }

    public boolean isManagerOfStore(String email, Long storeId) {
        return userRepository.findByEmail(email)
                .map(user -> user.getManagedStores().stream()
                        .anyMatch(store -> store.getId().equals(storeId)))
                .orElse(false);
    }

    // get the stores managed by a user
    public Set<Store> getManagedStores(String email) {
        return userRepository.findByEmail(email)
                .map(User::getManagedStores)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public Set<Store> getManagedStores(Long userId) {
        return userRepository.findById(userId)
                .map(User::getManagedStores)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    // add a store to the stores managed by a user, by user id and store id
    @Transactional
    public User addManagedStore(Long userId, Long storeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));

        Hibernate.initialize(user.getManagedStores());

        user.addManagedStore(store);
        return userRepository.save(user);
    }

    // remove a store from the stores managed by a user, by user id and store id
    public User removeManagedStore(Long userId, Long storeId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.removeManagedStore(storeRepository.findById(storeId)
                            .orElseThrow(() -> new ResourceNotFoundException("Store not found")));
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public void addManagedStore(String email, Store store) {
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    user.addManagedStore(store);
                    userRepository.save(user);
                });
    }
}
