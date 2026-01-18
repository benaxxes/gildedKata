package com.gildedrose.item;


class Sulfuras extends AbstractItem {
    public Sulfuras(Item item) {
        super(item);
    }

    @Override
    public void updateOneDay() {
        updateQuality();
    }

    private void updateQuality() {
        if (isExpired()) {
            getItem().quality = 0;
        }
    }
}
