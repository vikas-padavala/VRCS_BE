package com.vrcs.livemenu.entities;

public class MenuItem implements Comparable<MenuItem> {

    public static final String AVAILABLE = "Avaliable";
    public static final String LIMITED_STOCK = "Limited Stock";
    public static final String OUT_OF_STOCK = "Out of Stock";

    private Item item;
    private String status;

    public MenuItem() {
        super();
    }

    public MenuItem(Item item, String status) {
        this.item = item;
        this.status = status;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Item getItem() {
        return this.item;
    }

    public String getStatus() {
        return this.status;
    }

    @Override
    public int compareTo(MenuItem o) {
        return this.getItem().getItemCategory().getPriority() - o.getItem().getItemCategory().getPriority();
    }

}
