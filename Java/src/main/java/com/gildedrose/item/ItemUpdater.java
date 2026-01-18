package com.gildedrose.item;

public interface ItemUpdater {
    void updateOneDay();

    Item getItem();

    default void updateSellIn() {
        getItem().sellIn--;
    }
}
