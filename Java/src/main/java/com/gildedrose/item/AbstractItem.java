package com.gildedrose.item;

import lombok.Getter;

abstract class AbstractItem implements ItemUpdater {
    protected static final int MIN_QUALITY = 0;
    protected static final int MAX_QUALITY = 50;
    @Getter
    private final Item item;

    protected abstract void updateQuality();

    protected AbstractItem(Item item) {
        this.item = item;
    }

    protected void decreaseSellIn() {
        getItem().sellIn--;
    }

    protected void increaseQuality() {
        getItem().quality = Math.min(getItem().quality + 1, MAX_QUALITY);
    }

    protected void decreaseQuality(int amount) {
        getItem().quality = Math.max(getItem().quality - amount, MIN_QUALITY);
    }

    protected boolean isExpired() {
        return isSellInSmallerThan(0);
    }

    protected boolean isSellInSmallerThan(int sellInValue) {
        return getItem().sellIn < sellInValue;
    }
}
