package com.gildedrose.item;


public abstract class LegendaryItem implements ItemUpdater {
    Item item;

    protected LegendaryItem(Item item) {
        this.item = item;
    }

    @Override
    public void updateOneDay() {
        item.quality = 80;
    }
}
