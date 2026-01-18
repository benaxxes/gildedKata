package com.gildedrose.item;

import lombok.Getter;

import java.util.function.Function;

public enum ItemType {
    AGED_BRIE("Aged Brie", AgedBrie::new),
    BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert", BackstagePass::new),
    SULFURAS("Sulfuras, Hand of Ragnaros", Sulfuras::new);

    @Getter
    private final String name;
    private final Function<Item, ItemUpdater> factory;

    ItemType(String name, Function<Item, ItemUpdater> factory) {
        this.name = name;
        this.factory = factory;
    }

    public ItemUpdater create(Item item) { return factory.apply(item); }
}
