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

    private void updateQuality() {
        if (getItem().quality > 0) {
            decreaseQuality(1);
        }
    }
}
