package com.vrcs.livemenu.services;

import java.util.List;

import com.vrcs.livemenu.payloads.ItemDto;

public interface ItemService {

    public ItemDto createItem(Long itemCategoryId, ItemDto itemDto);

    public List<ItemDto> getAllItems();

    public List<ItemDto> getAllItemsByCategoryId(Long itemCategoryId);

    public void deleteItemById(Long itemId);
}
