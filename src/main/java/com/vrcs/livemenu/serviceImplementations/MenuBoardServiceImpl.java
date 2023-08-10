package com.vrcs.livemenu.serviceImplementations;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vrcs.livemenu.entities.Item;
import com.vrcs.livemenu.entities.MenuBoard;
import com.vrcs.livemenu.entities.MenuItem;
import com.vrcs.livemenu.exceptions.ItemNotFound;
import com.vrcs.livemenu.exceptions.StatusCodeInvalid;
import com.vrcs.livemenu.payloads.ItemListDto;
import com.vrcs.livemenu.repositories.ItemRepository;
import com.vrcs.livemenu.services.MenuBoardService;

@Service
public class MenuBoardServiceImpl implements MenuBoardService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MenuBoard menuBoard;

    @Override
    public void addMenuItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFound(String.format("Item with Id %d not found", itemId)));
        MenuItem menuItem = new MenuItem(item, MenuItem.AVAILABLE);
        menuBoard.add(menuItem);
    }

    @Override
    public Set<MenuItem> getMenu() {
        return menuBoard.getMenu();
    }

    @Override
    public void addItemList(ItemListDto itemListDto) {
        List<Long> menuList = itemListDto.getMenuList();
        for (Long itemId : menuList) {
            Item item = itemRepository.findById(itemId)
                    .orElseThrow(() -> new ItemNotFound(String.format("Item with Id %d not found", itemId)));
            MenuItem menuItem = new MenuItem(item, MenuItem.AVAILABLE);
            menuBoard.add(menuItem);
        }
    }

    @Override
    public void updateItemStatus(Long itemId, Integer status) {
        Set<MenuItem> menu = menuBoard.getMenu();
        MenuItem targetMenuItem = null;
        for (MenuItem menuItem : menu) {
            if (menuItem.getItem().getId().equals(itemId)) {
                targetMenuItem = menuItem;
            }
        }
        if (targetMenuItem == null) {
            throw (new ItemNotFound(String.format("Item with Id %d not found on menu.", itemId)));
        }
        if (status.equals(Integer.valueOf(1))) {
            targetMenuItem.setStatus(MenuItem.AVAILABLE);
        } else if (status.equals(Integer.valueOf(2))) {
            targetMenuItem.setStatus(MenuItem.LIMITED_STOCK);
        } else if (status.equals(Integer.valueOf(3))) {
            menu.remove(targetMenuItem);
        } else {
            throw (new StatusCodeInvalid(String.format("Status with status code %d not found.", status)));
        }
    }

}
