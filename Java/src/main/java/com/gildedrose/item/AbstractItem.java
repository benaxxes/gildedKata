package com.gildedrose.item;

import lombok.Getter;

public abstract class AbstractItem implements ItemUpdater {
    public static final int MAX_QUALITY = 50;
    @Getter
    Item item;

    protected AbstractItem(Item item) {
        this.item = item;
    }

    protected void decreaseSellIn() {
        getItem().sellIn--;
    }

    protected boolean isQualitySmallerThan() {
        return getItem().quality < MAX_QUALITY;
    }

    protected void increaseQuality(int i) {
        getItem().quality = getItem().quality + i;
    }

    protected void decreaseQuality(int i) {
        getItem().quality = getItem().quality - i;
    }

    protected boolean isSellInSmallerThan(int sellInValue) {
        return getItem().sellIn < sellInValue;
    }

    protected boolean isExpired() {
        return isSellInSmallerThan(0);
    }
}
