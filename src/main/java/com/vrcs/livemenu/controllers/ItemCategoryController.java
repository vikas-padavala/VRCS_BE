package com.vrcs.livemenu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vrcs.livemenu.payloads.ItemCategoryDto;
import com.vrcs.livemenu.services.ItemCategoryService;

@RestController
@RequestMapping("/api")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    @PostMapping("/itemCategory")
    public ResponseEntity<ItemCategoryDto> createItemCategory(
            @RequestBody ItemCategoryDto itemCategoryDto) {
        return new ResponseEntity<ItemCategoryDto>(itemCategoryService.createItemCategory(itemCategoryDto),
                HttpStatus.CREATED);
    }

    @GetMapping("/itemCategory")
    public ResponseEntity<List<ItemCategoryDto>> getAllItemCategories() {
        return new ResponseEntity<List<ItemCategoryDto>>(itemCategoryService.getAllItemCategories(),
                HttpStatus.OK);
    }

}

// /api/{userId}/itemCategory