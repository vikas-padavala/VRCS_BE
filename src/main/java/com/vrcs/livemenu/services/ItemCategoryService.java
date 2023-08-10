package com.vrcs.livemenu.services;

import java.util.List;

import com.vrcs.livemenu.payloads.ItemCategoryDto;

public interface ItemCategoryService {

    public ItemCategoryDto createItemCategory(ItemCategoryDto itemCategoryDto);

    public List<ItemCategoryDto> getAllItemCategories();
}
