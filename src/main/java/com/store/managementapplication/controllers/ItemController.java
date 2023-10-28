package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.Item;
import com.store.managementapplication.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    // Other CRUD endpoints
}