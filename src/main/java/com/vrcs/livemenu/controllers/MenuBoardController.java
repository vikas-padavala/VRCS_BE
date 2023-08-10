package com.vrcs.livemenu.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.vrcs.livemenu.entities.MenuItem;
import com.vrcs.livemenu.payloads.ItemListDto;
import com.vrcs.livemenu.services.MenuBoardService;

@RestController
@RequestMapping("/api")
public class MenuBoardController {
    @Autowired
    MenuBoardService menuBoardService;

    @PostMapping("/menuBoard/{itemId}")
    public ResponseEntity<String> addMenuItem(@PathVariable("itemId") Long itemId) {
        menuBoardService.addMenuItem(itemId);
        return new ResponseEntity<String>("Menu Item added successfully!", HttpStatus.ACCEPTED);
    }

    // add a list of menu items
    @PostMapping("/menuBoard")
    public ResponseEntity<String> addListOfItems(@RequestBody ItemListDto itemListDto) {
        menuBoardService.addItemList(itemListDto);
        return new ResponseEntity<String>("List of Menu Items added successfully", HttpStatus.ACCEPTED);
    }

    // get menu board
    @GetMapping("/menuBoard")
    public ResponseEntity<Set<MenuItem>> getMenu() {
        return new ResponseEntity<Set<MenuItem>>(menuBoardService.getMenu(), HttpStatus.OK);
    }

    @PostMapping("/menuBoard/{itemId}/{status}")
    public ResponseEntity<String> changeItemStatus(@PathVariable("itemId") Long itemId,
            @PathVariable("status") Integer status) {
        menuBoardService.updateItemStatus(itemId, status);
        return new ResponseEntity<String>("Menu Board updated", HttpStatus.OK);
    }
}
