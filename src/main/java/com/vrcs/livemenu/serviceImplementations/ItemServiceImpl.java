package com.vrcs.livemenu.serviceImplementations;

import com.vrcs.livemenu.entities.Item;
import com.vrcs.livemenu.entities.ItemCategory;
import com.vrcs.livemenu.exceptions.ItemCategoryNotFound;
import com.vrcs.livemenu.exceptions.ItemNotFound;
import com.vrcs.livemenu.payloads.ItemDto;
import com.vrcs.livemenu.repositories.ItemCategoryRepository;
import com.vrcs.livemenu.repositories.ItemRepository;
import com.vrcs.livemenu.services.ItemService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemCategoryRepository itemCategoryRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public ItemDto createItem(Long itemCategoryId, ItemDto itemDto) {
        ItemCategory itemCategory = itemCategoryRepository.findById(itemCategoryId).orElseThrow(
                () -> new ItemCategoryNotFound(String.format("Item Category with id %d not found", itemCategoryId)));
        Item item = itemDtoToItem(itemDto);
        item.setItemCategory(itemCategory);
        item = itemRepository.save(item);
        ItemDto savedItemDto = itemToItemDto(item);
        return savedItemDto;
    }

    @Override
    public List<ItemDto> getAllItems() {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemDtos = new ArrayList<ItemDto>(items.size());
        for (Item item : items) {
            itemDtos.add(itemToItemDto(item));
        }
        return itemDtos;
    }

    @Override
    public List<ItemDto> getAllItemsByCategoryId(Long itemCategoryId) {
        ItemCategory itemCategory = itemCategoryRepository.findById(itemCategoryId).orElseThrow(
                () -> new ItemCategoryNotFound(String.format("Item Category with id %d not found", itemCategoryId)));
        List<Item> items = itemRepository.findByItemCategory(itemCategory);
        List<ItemDto> itemDtos = new ArrayList<ItemDto>(items.size());
        for (Item item : items) {
            itemDtos.add(itemToItemDto(item));
        }
        return itemDtos;
    }

    @Override
    public void deleteItemById(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFound(String.format("Item with Id %d not found", itemId)));
        itemRepository.delete(item);
    }

    private ItemDto itemToItemDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setPrice(item.getPrice());
        itemDto.setItemCategory(item.getItemCategory());
        return itemDto;
    }

    private Item itemDtoToItem(ItemDto itemDto) {
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        return item;
    }

}
