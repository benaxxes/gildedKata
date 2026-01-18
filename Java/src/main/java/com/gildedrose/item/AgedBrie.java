package com.gildedrose.item;

class AgedBrie extends AbstractItem {
    protected AgedBrie(Item item) {
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

    private void updateQuality() {
        increaseQuality();
    }
}
