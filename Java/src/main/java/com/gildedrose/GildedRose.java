package com.gildedrose;

import static com.gildedrose.ItemType.*;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private static void updateItem(Item item) {
        if (!item.name.equals(AGED_BRIE.getName()) && !item.name.equals(BACKSTAGE_PASSES.getName())) {
            if (item.quality > 0 && !item.name.equals(SULFURAS.getName())) {
                item.quality--;
            }
        } else {
            if (item.quality < 50) {
                item.quality++;

                if (item.name.equals(BACKSTAGE_PASSES.getName())) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality++;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality++;
                        }
                    }
                }
            }
        }

        if (!item.name.equals(SULFURAS.getName())) {
            item.sellIn--;
        }

        if (item.sellIn < 0) {
            if (!item.name.equals(AGED_BRIE.getName())) {
                if (!item.name.equals(BACKSTAGE_PASSES.getName())) {
                    if (item.quality > 0) {
                        if (!item.name.equals(SULFURAS.getName())) {
                            item.quality--;
                        }
                    }
                } else {
                    item.quality = 0;
                }
            } else {
                if (item.quality < 50) {
                    item.quality++;
                }
            }
        }
    }
}
