package com.gildedrose;

import com.gildedrose.item.Item;
import com.gildedrose.item.ItemFactory;
import com.gildedrose.item.ItemUpdater;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }


    public void updateQuality() {
        for (Item item : items) {
            ItemUpdater itemUpdater = ItemFactory.newItem(item);
            itemUpdater.updateOneDay();
        }
    }

}
