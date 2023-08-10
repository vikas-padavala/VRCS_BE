package com.vrcs.livemenu.payloads;

import com.vrcs.livemenu.entities.ItemCategory;

import lombok.Data;

@Data
public class ItemDto {

    private Long id;

    private String name;

    private int price;

    private ItemCategory itemCategory;
}
