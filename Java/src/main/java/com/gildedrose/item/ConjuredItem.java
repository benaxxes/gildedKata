package com.gildedrose.item;


public class ConjuredItem extends AbstractItem {

    protected ConjuredItem(Item item) {
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
            decreaseQuality(2);
    }
}
