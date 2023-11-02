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

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Item> items = new HashSet<>();

    public void addItem(Item item) {
        items.add(item);
        item.setCategory(this);
    }
}
