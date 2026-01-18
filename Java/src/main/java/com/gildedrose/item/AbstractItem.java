package com.gildedrose.item;

import lombok.Getter;

abstract class AbstractItem implements ItemUpdater {
    static final int MIN_QUALITY = 0;
    static final int MAX_QUALITY = 50;
    @Getter
    private final Item item;

    abstract void updateQuality();

    AbstractItem(Item item) {
        this.item = item;
    }

    boolean isExpired() {
        return isSellInSmallerThan(0);
    }

    final void decreaseSellIn() {
        getItem().sellIn--;
    }

    final void increaseQuality(int amount) {
        getItem().quality = Math.min(getItem().quality + amount, MAX_QUALITY);
    }

    final void increaseQuality() {
        increaseQuality(1);
    }

    final void decreaseQuality(int amount) {
        getItem().quality = Math.max(getItem().quality - amount, MIN_QUALITY);
    }

    final boolean isSellInSmallerThan(int sellInValue) {
        return getItem().sellIn < sellInValue;
    }
}
