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
            ItemUpdater mappedItem = ItemFactory.newItem(item);
            updateQualityForItem(mappedItem);
        }
    }

    private void updateQualityForItem(ItemUpdater item) {
        item.updateOneDay();
    }

}
