package com.store.managementapplication.services;

import com.store.managementapplication.entities.Item;
import com.store.managementapplication.entities.Store;
import com.store.managementapplication.entities.StoreInventory;
import com.store.managementapplication.repositories.ItemRepository;
import com.store.managementapplication.repositories.StoreInventoryRepository;
import com.store.managementapplication.repositories.StoreRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StoreInventoryService {


    private final StoreInventoryRepository storeInventoryRepository;
    private final StoreRepository storeRepository;
    private final ItemRepository itemRepository;

    public StoreInventoryService(StoreInventoryRepository storeInventoryRepository, StoreRepository storeRepository, ItemRepository itemRepository) {
        this.storeInventoryRepository = storeInventoryRepository;
        this.storeRepository = storeRepository;
        this.itemRepository = itemRepository;
    }

    public List<StoreInventory> getAllInventories() {
        return storeInventoryRepository.findAll();
    }

    public Optional<StoreInventory> getInventoryById(Long id) {
        return storeInventoryRepository.findById(id);
    }

    public StoreInventory saveInventory(StoreInventory storeInventory) {
        return storeInventoryRepository.save(storeInventory);
    }

    public void deleteInventory(Long id) {
        storeInventoryRepository.deleteById(id);
    }

    @Transactional
    public StoreInventory addItemToStoreInventory(Long storeId, Long itemId, int count, String status) {
        StoreInventory inventory = getInventoryByItemIdAndStoreId(itemId, storeId);
        Store store = storeRepository.findById(storeId).orElseThrow();
        Hibernate.initialize(store.getStoreInventories());
        Item item = itemRepository.findById(itemId).orElseThrow();
        Hibernate.initialize(item.getStoreInventories());

        StoreInventory savedInventory = null;

        if (inventory == null) {
            inventory = new StoreInventory(store, item, count, status);
            savedInventory = storeInventoryRepository.save(inventory);
        } else {
            inventory.addItems(count);
            savedInventory = storeInventoryRepository.save(inventory);
        }

        savedInventory = storeInventoryRepository.save(inventory);

        // set each store to null, in each store inventory to avoid infinite recursion
        //savedInventory.setStore(savedInventory.getStore().getStoreInventories());

        return savedInventory;
    }

    public StoreInventory getInventoryByItemIdAndStoreId(Long itemId, Long storeId) {
        // if exists, return the inventory
        // else, create a new inventory and return it
        return storeInventoryRepository.findByItemIdAndStoreId(itemId, storeId);
    }

    public boolean isInventoryBelowThreshold(Long id, int threshold) {
        StoreInventory inventory = getInventoryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid inventory ID"));
        return inventory.isBelowThreshold(threshold);
    }
}
