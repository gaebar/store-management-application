package com.store.managementapplication.services;

import com.store.managementapplication.entities.Item;
import com.store.managementapplication.entities.PurchaseOrder;
import com.store.managementapplication.entities.Store;
import com.store.managementapplication.entities.User;
import com.store.managementapplication.entities.Role;
import com.store.managementapplication.repositories.ItemRepository;
import com.store.managementapplication.repositories.PurchaseOrderRepository;
import com.store.managementapplication.repositories.StoreRepository;
import com.store.managementapplication.repositories.UserRepository;
import com.store.managementapplication.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // Methods related to Stores

    // Create a new Store
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    // Update an existing Store
    public Store updateStore(Long id, Store store) throws Exception {
        if (storeRepository.existsById(id)) {
            store.setId(id);
            return storeRepository.save(store);
        } else {
            throw new Exception("Store not found");
        }
    }

    // Delete a Store by its ID
    public void deleteStore(Long id) throws Exception {
        if (storeRepository.existsById(id)) {
            storeRepository.deleteById(id);
        } else {
            throw new Exception("Store not found");
        }
    }

    // Methods related to Users

    // Create a new User
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Update an existing User
    public User updateUser(Long id, User user) throws Exception {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        } else {
            throw new Exception("User not found");
        }
    }

    // Delete a User by its ID
    public void deleteUser(Long id) throws Exception {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new Exception("User not found");
        }
    }

    // Methods related to Items

    // Create a new Item
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    // Update an existing Item
    public Item updateItem(Long id, Item item) throws Exception {
        if (itemRepository.existsById(id)) {
            item.setId(id);
            return itemRepository.save(item);
        } else {
            throw new Exception("Item not found");
        }
    }

    // Delete an Item by its ID
    public void deleteItem(Long id) throws Exception {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
        } else {
            throw new Exception("Item not found");
        }
    }

    // Methods related to Purchase Orders

    // Create a new Purchase Order
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    // Update an existing Purchase Order
    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder purchaseOrder) throws Exception {
        if (purchaseOrderRepository.existsById(id)) {
            purchaseOrder.setId(id);
            return purchaseOrderRepository.save(purchaseOrder);
        } else {
            throw new Exception("Purchase Order not found");
        }
    }

    // Delete a Purchase Order by its ID
    public void deletePurchaseOrder(Long id) throws Exception {
        if (purchaseOrderRepository.existsById(id)) {
            purchaseOrderRepository.deleteById(id);
        } else {
            throw new Exception("Purchase Order not found");
        }
    }

    // Get all Purchase Orders
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    // Methods related to Roles

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
}
