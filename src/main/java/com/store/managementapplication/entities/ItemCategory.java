package com.store.managementapplication.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

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

    @Override
    public String toString() {
        return "ItemCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", supplier='" + supplier + '\'' +
                '}';
    }
}
