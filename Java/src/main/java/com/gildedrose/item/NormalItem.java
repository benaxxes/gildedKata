package com.gildedrose.item;

import lombok.Getter;

class NormalItem implements ItemUpdater {
    @Getter
    Item item;

    public NormalItem(Item item) {
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
        if (getItem().quality > 0) {
            getItem().quality--;
        }
    }
}
