package com.gildedrose.item;


public abstract class LegendaryItem implements ItemUpdater {
    private static final int LEGENDARY_QUALITY = 80;

    private final Item item;

    protected LegendaryItem(Item item) {
        this.item = item;
    }

    @Override
    public void updateOneDay() {
        item.quality = LEGENDARY_QUALITY;
    }
}
