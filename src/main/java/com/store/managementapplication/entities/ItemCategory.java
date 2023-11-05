package com.store.managementapplication.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String supplier;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Item> items = new HashSet<>();

    public ItemCategory(Long id) {
        this.id = id;
    }

    public ItemCategory(Long id, String name, String supplier) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
    }

    public void addItem(Item item) {
        items.add(item);
        item.setCategory(this);
    }
}
