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
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    public Store updateStore(Long id, Store store) {
        if (storeRepository.existsById(id)) {
            store.setId(id);
            return storeRepository.save(store);
        }
        return null;
    }

    public void deleteStore(Long id) {
        if (storeRepository.existsById(id)) {
            storeRepository.deleteById(id);
        }
    }

    // Methods related to Users
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    // Methods related to Items and Categories
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item item) {
        if (itemRepository.existsById(id)) {
            item.setId(id);
            return itemRepository.save(item);
        }
        return null;
    }

    public void deleteItem(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
        }
    }

    // Methods related to Purchase Orders
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public Optional<PurchaseOrder> getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id);
    }

    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder purchaseOrder) {
        if (purchaseOrderRepository.existsById(id)) {
            purchaseOrder.setId(id);
            return purchaseOrderRepository.save(purchaseOrder);
        }
        return null;
    }

    public void deletePurchaseOrder(Long id) {
        if (purchaseOrderRepository.existsById(id)) {
            purchaseOrderRepository.deleteById(id);
        }
    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    // Methods related to Roles (Added)
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role role) {
        if (roleRepository.existsById(id)) {
            role.setId(id);
            return roleRepository.save(role);
        }
        return null;
    }

    public void deleteRole(Long id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
        }
    }
}
