package com.gildedrose.item;

import lombok.Getter;

class AgedBrie implements ItemI {
    @Getter
    Item item;

    public AgedBrie(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        if (getItem().quality < 50) {
            getItem().quality++;
        }

        getItem().sellIn--;

        if (getItem().sellIn < 0 && getItem().quality < 50) {
            getItem().quality++;
        }
    }
}
