package com.gildedrose.item;


import lombok.Getter;

class BackstagePass implements ItemUpdater {
    @Getter
    Item item;

    public BackstagePass(Item item) {
        this.item = item;
    }

    @Override
    public void updateOneDay() {
        updateQuality();

        updateSellIn();

        if (getItem().sellIn < 0) {
            getItem().quality = 0;
        }
    }

    private void updateQuality() {
        if (getItem().quality < 50) {
            getItem().quality++;

            if (getItem().sellIn < 11 && getItem().quality < 50) {
                getItem().quality++;
            }

            if (getItem().sellIn < 6 && getItem().quality < 50) {
                getItem().quality++;
            }
        }
    }
}
