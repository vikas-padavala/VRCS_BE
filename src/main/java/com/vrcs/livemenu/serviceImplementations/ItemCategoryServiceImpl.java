package com.vrcs.livemenu.serviceImplementations;

import com.vrcs.livemenu.entities.ItemCategory;
import com.vrcs.livemenu.payloads.ItemCategoryDto;
import com.vrcs.livemenu.repositories.ItemCategoryRepository;
import com.vrcs.livemenu.services.ItemCategoryService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Override
    public ItemCategoryDto createItemCategory(ItemCategoryDto itemCategoryDto) {
        ItemCategory itemCategory = itemCategoryDtoToItemCategory(itemCategoryDto);
        itemCategory = itemCategoryRepository.save(itemCategory);
        ItemCategoryDto savedItemCategoryDto = itemCategoryToItemCategoryDto(itemCategory);
        return savedItemCategoryDto;
    }

    @Override
    public List<ItemCategoryDto> getAllItemCategories() {
        List<ItemCategory> itemCategories = itemCategoryRepository.findAll();
        List<ItemCategoryDto> itemCategoryDtos = new ArrayList<ItemCategoryDto>(itemCategories.size());
        for (ItemCategory itemCategory : itemCategories) {
            itemCategoryDtos.add(itemCategoryToItemCategoryDto(itemCategory));
        }
        return itemCategoryDtos;
    }

    private ItemCategory itemCategoryDtoToItemCategory(ItemCategoryDto itemCategoryDto) {
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setName(itemCategoryDto.getName());
        itemCategory.setPriority(itemCategoryDto.getPriority());
        return itemCategory;
    }

    private ItemCategoryDto itemCategoryToItemCategoryDto(ItemCategory itemCategory) {
        ItemCategoryDto itemCategoryDto = new ItemCategoryDto();
        itemCategoryDto.setId(itemCategory.getId());
        itemCategoryDto.setName(itemCategory.getName());
        itemCategoryDto.setPriority(itemCategory.getPriority());
        return itemCategoryDto;
    }

}
