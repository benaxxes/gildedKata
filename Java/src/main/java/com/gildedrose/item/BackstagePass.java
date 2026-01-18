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
        int qualityIncrease = calculateQualityIncrease();
        increaseQuality(qualityIncrease);
    }

    private int calculateQualityIncrease() {
        if (isSellInSmallerThan(6)) {
            return 3;
        } else if (isSellInSmallerThan(11)) {
            return 2;
        }
        return 1;
    }
}
