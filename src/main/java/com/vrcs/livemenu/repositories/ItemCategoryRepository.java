package com.vrcs.livemenu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vrcs.livemenu.entities.ItemCategory;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {

}
