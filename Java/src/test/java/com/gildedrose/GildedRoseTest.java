package com.gildedrose;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {


    private static Stream<Arguments> provideSpecialItems() {
        return Stream.of(
            Arguments.of("Aged Brie", 1, 9, 21),
            Arguments.of("Aged Brie", 10, 0, 30),
            Arguments.of("Aged Brie", 11, -1, 32),
            Arguments.of("Aged Brie", 100, -90, 50),

            // Sulfuras never has to be sold and never decreases in Quality
            Arguments.of("Sulfuras, Hand of Ragnaros", 1, 10, 20),
            Arguments.of("Sulfuras, Hand of Ragnaros", 10, 10, 20),


            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 1, 9, 22),
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 6, 4, 33),
            Arguments.of("Backstage passes to a TAFKAL80ETC concert", 11, -1, 0)
        );
    }

    private static Stream<Arguments> provideTimesAndExpectedQualityAndSellIn() {
        return Stream.of(
            Arguments.of(1, 9, 19),
            Arguments.of(2, 8, 18),
            Arguments.of(10, 0, 10),
            // quality degrades twice as fast after sellIn date
            Arguments.of(11, -1, 8),
            // quality should not go negative
            Arguments.of(20, -10, 0),
            Arguments.of(25, -15, 0)
        );
    }

    @ParameterizedTest(name = "After {0} days, sellIn should be {1} and quality should be {2}")
    @MethodSource("provideTimesAndExpectedQualityAndSellIn")
    void normalItemUpdateTest(int times, int expectedSellIn, int expectedQuality) {
        // given
        Item[] items = new Item[]{new Item("Normal Item", 10, 20)};
        GildedRose gildedRose = new GildedRose(items);

        // when
        updateQuality(gildedRose, times);

        // then
        assertThat(gildedRose.items[0].sellIn).isEqualTo(expectedSellIn);
        assertThat(gildedRose.items[0].quality).isEqualTo(expectedQuality);
    }

    @ParameterizedTest(name = "Item: \"{0}\" - After {1} days, sellIn should be {2} and quality should be {3}")
    @MethodSource("provideSpecialItems")
    void specialItemUpdateTest(String itemName, int times, int expectedSellIn, int expectedQuality) {
        // given
        Item[] items = new Item[]{new Item(itemName, 10, 20)};
        GildedRose gildedRose = new GildedRose(items);

        // when
        updateQuality(gildedRose, times);

        // then
        assertThat(gildedRose.items[0].sellIn).isEqualTo(expectedSellIn);
        assertThat(gildedRose.items[0].quality).isEqualTo(expectedQuality);
    }

    private void updateQuality(GildedRose gildedRose, int times) {
        for (int i = 0; i < times; i++) {
            gildedRose.updateQuality();
        }
    }


}
