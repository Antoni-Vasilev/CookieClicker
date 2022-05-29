package com.example.cookieclickerv2.Model;

public class Upgrade {

    private final int image;
    private final String title;
    private final String id;
    private final int maxLevel;
    private final String startPrice;
    private final String startAddMoney;
    private final String addMoney;
    private final boolean isFirst;

    public Upgrade(int image, String title, String id, int maxLevel, String startPrice, String startAddPrice, String addMoney, boolean isFirst) {
        this.startAddMoney = startAddPrice;
        this.startPrice = startPrice;
        this.maxLevel = maxLevel;
        this.image = image;
        this.title = title;
        this.id = id;
        this.addMoney = addMoney;
        this.isFirst = isFirst;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public String getStartPrice() {
        return startPrice;
    }

    public String getStartAddMoney() {
        return startAddMoney;
    }

    public String getAddMoney() {
        return addMoney;
    }

    public boolean isFirst() {
        return isFirst;
    }
}
