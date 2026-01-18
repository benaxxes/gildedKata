package com.gildedrose.item;

import lombok.Getter;

class AgedBrie implements ItemUpdater {
    @Getter
    Item item;

    public AgedBrie(Item item) {
        this.item = item;
    }

    @Override
    public void updateOneDay() {
        updateQuality();
        updateSellIn();

        if (getItem().sellIn < 0) {
            updateQuality();
        }
    }

    private void updateQuality() {
        if (getItem().quality < 50) {
            getItem().quality++;
        }
    }
}
