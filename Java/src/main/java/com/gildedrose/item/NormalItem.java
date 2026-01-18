package com.gildedrose.item;

class NormalItem extends AbstractItem {
    public NormalItem(Item item) {
        super(item);
    }

    @Override
    public void updateOneDay() {
        updateQuality();
        decreaseSellIn();

        if (isExpired()) {
            updateQuality();
        }

    }

    @Override
    protected void updateQuality() {
        decreaseQuality(1);
    }
}
