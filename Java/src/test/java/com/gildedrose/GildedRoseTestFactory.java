package com.gildedrose;

import com.gildedrose.item.Item;
import com.gildedrose.item.ItemType;

public class GildedRoseTestFactory {

    static GildedRose createWithItem(String name, int sellIn, int quality) {
        Item[] items = new Item[]{new Item(name, sellIn, quality)};
        return new GildedRose(items);
    }

    static GildedRose createWithItem(ItemType itemType, int sellIn, int quality) {
        return createWithItem(itemType.getName(), sellIn, quality);
    }

    static GildedRose createWithItems(Item... items) {
        return new GildedRose(items);
    }
}
