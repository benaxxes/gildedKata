package com.gildedrose;

import com.gildedrose.item.Item;
import com.gildedrose.item.ItemFactory;
import com.gildedrose.item.ItemI;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }


    public void updateQuality() {
        for (Item item : items) {
            ItemI mappedItem = ItemFactory.newItem(item);
            updateQualityForItem(mappedItem);
        }
    }

    private void updateQualityForItem(ItemI item) {
        item.updateQuality();
    }

}
