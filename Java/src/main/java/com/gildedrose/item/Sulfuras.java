package com.gildedrose.item;


import lombok.Getter;

class Sulfuras implements ItemUpdater {
    @Getter
    Item item;

    public Sulfuras(Item item) {
        this.item = item;
    }

    @Override
    public void updateOneDay() {
        updateQuality();
    }

    private void updateQuality() {
        if (getItem().sellIn < 0) {
            getItem().quality = 0;
        }
    }
}
