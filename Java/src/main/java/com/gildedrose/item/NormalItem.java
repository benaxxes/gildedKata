package com.gildedrose.item;

import lombok.Getter;

class NormalItem implements ItemI {
    @Getter
    Item item;

    public NormalItem(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {
        if (getItem().quality > 0) {
            getItem().quality--;
        }

        getItem().sellIn--;

        if (getItem().sellIn < 0 && getItem().quality > 0) {
            getItem().quality--;
        }

    }
}
