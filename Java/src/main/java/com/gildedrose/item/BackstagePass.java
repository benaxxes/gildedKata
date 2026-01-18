package com.gildedrose.item;


class BackstagePass extends AbstractItem {
    public BackstagePass(Item item) {
        super(item);
    }

    @Override
    public void updateOneDay() {
        updateQuality();
        decreaseSellIn();

        if (isExpired()) {
            getItem().quality = 0;
        }
    }

    @Override
    protected void updateQuality() {
        increaseQuality();

        if (isSellInSmallerThan(11)) {
            increaseQuality();
        }

        if (isSellInSmallerThan(6)) {
            increaseQuality();
        }
    }
}
