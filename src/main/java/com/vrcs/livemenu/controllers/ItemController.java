package com.vrcs.livemenu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vrcs.livemenu.payloads.ItemDto;
import com.vrcs.livemenu.services.ItemService;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/{itemCategoryId}/item")
    public ResponseEntity<ItemDto> createItem(@PathVariable("itemCategoryId") Long itemCategoryId,
            @RequestBody ItemDto itemDto) {
        return new ResponseEntity<ItemDto>(itemService.createItem(itemCategoryId, itemDto), HttpStatus.CREATED);
    }

    @GetMapping("/item")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        return new ResponseEntity<List<ItemDto>>(itemService.getAllItems(), HttpStatus.OK);
    }

    @GetMapping("/{itemCategoryId}/item")
    public ResponseEntity<List<ItemDto>> getAllItemsByCategoryId(@PathVariable("itemCategoryId") Long itemCategoryId) {
        return new ResponseEntity<List<ItemDto>>(itemService.getAllItemsByCategoryId(itemCategoryId), HttpStatus.OK);
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable("itemId") Long itemId) {
        itemService.deleteItemById(itemId);
        return new ResponseEntity<String>("The Item has been deleted", HttpStatus.OK);
    }

}

// /api/{itemCategoryId}/item