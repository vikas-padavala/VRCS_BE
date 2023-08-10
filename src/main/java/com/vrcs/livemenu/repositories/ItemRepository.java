package com.vrcs.livemenu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vrcs.livemenu.entities.Item;
import com.vrcs.livemenu.entities.ItemCategory;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    public List<Item> findByItemCategory(ItemCategory itemCategory);
}
