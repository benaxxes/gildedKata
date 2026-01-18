package com.gildedrose.item;


public class ConjuredItem extends AbstractItem {

    protected ConjuredItem(Item item) {
        super(item);
    }

    @Override
    public void updateOneDay() {
        updateQuality();
        updateQuality();
        decreaseSellIn();

        if (isSellInSmallerThan(0)) {
            updateQuality();
            updateQuality();
        }
    }

    private void updateQuality() {
            decreaseQuality();
    }
}
