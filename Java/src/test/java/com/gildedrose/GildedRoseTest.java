package com.gildedrose;

import com.gildedrose.item.Item;
import com.gildedrose.item.ItemType;
import net.datafaker.Faker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.gildedrose.item.ItemType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    private static final Faker faker = new Faker();

    @ParameterizedTest(name = "After {0} days, sellIn should be {1} and quality should be {2}")
    @MethodSource("provideTimesAndExpectedQualityAndSellIn")
    void normalItemUpdateTest(int times, int expectedSellIn, int expectedQuality) {
        // given
        Item[] items = new Item[]{new Item(faker.funnyName().name(), 10, 20)};
        GildedRose gildedRose = new GildedRose(items);

        // when
        updateQuality(gildedRose, times);

        // then
        assertEquals(expectedSellIn, gildedRose.items[0].sellIn);
        assertEquals(expectedQuality, gildedRose.items[0].quality);
    }

    @ParameterizedTest(name = "Item: \"{0}\" - After {1} days, sellIn should be {2} and quality should be {3}")
    @MethodSource("provideSpecialItems")
    void specialItemUpdateTest(ItemType itemName, int times, int expectedSellIn, int expectedQuality) {
        // given
        Item[] items = new Item[]{new Item(itemName.getName(), 10, 20)};
        GildedRose gildedRose = new GildedRose(items);

        // when
        updateQuality(gildedRose, times);

        // then
        assertEquals(expectedSellIn, gildedRose.items[0].sellIn);
        assertEquals(expectedQuality, gildedRose.items[0].quality);
    }

    @ParameterizedTest(name = "After {0} days, sellIn should be {1} and quality should be {2}")
    @MethodSource("provideTimesAndExpectedQualityAndSellInConjured")
    void conjuredItemUpdateTest(int times, int expectedSellIn, int expectedQuality) {
        // given
        Item[] items = new Item[]{new Item("Conjured" + faker.funnyName().name(), 10, 20)};
        GildedRose gildedRose = new GildedRose(items);

        // when
        updateQuality(gildedRose, times);

        // then
        assertEquals(expectedSellIn, gildedRose.items[0].sellIn);
        assertEquals(expectedQuality, gildedRose.items[0].quality);
    }


    private void updateQuality(GildedRose gildedRose, int times) {
        for (int i = 0; i < times; i++) {
            gildedRose.updateQuality();
        }
    }

    private static Stream<Arguments> provideSpecialItems() {
        return Stream.of(
            Arguments.of(AGED_BRIE, 1, 9, 21),
            Arguments.of(AGED_BRIE, 10, 0, 30),
            Arguments.of(AGED_BRIE, 11, -1, 32),
            Arguments.of(AGED_BRIE, 100, -90, 50),
            Arguments.of(SULFURAS, 1, 10, 20),
            Arguments.of(SULFURAS, 10, 10, 20),
            Arguments.of(BACKSTAGE_PASSES, 1, 9, 22),
            Arguments.of(BACKSTAGE_PASSES, 6, 4, 33),
            Arguments.of(BACKSTAGE_PASSES, 11, -1, 0)
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

    public static Stream<Arguments> provideTimesAndExpectedQualityAndSellInConjured() {
        return Stream.of(
            Arguments.of(1, 9, 18),
            Arguments.of(2, 8, 16),
            Arguments.of(10, 0, 0),
            // quality should not go negative
            Arguments.of(11, -1, 0),
            Arguments.of(20, -10, 0),
            Arguments.of(25, -15, 0)
        );
    }

}
