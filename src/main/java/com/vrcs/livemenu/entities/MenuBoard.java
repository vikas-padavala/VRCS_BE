package com.vrcs.livemenu.entities;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

@Component("menuBoard")
public class MenuBoard {

    private Set<MenuItem> menu = new TreeSet<MenuItem>();

    public void add(MenuItem menuItem) {
        this.menu.add(menuItem);
    }

    public Set<MenuItem> getMenu() {
        return menu;
    }
}
