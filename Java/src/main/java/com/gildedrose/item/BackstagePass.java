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

    private void updateQuality() {
        if (getItem().quality < 50) {
            increaseQuality(1);

            if (isSellInSmallerThan(11) && isQualitySmallerThan()) {
                increaseQuality(1);
            }

            if (isSellInSmallerThan(6) && isQualitySmallerThan()) {
                increaseQuality(1);
            }
        }
    }
}
