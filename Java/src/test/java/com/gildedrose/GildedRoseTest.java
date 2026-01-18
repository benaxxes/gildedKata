package com.gildedrose;

import com.gildedrose.item.Item;
import com.gildedrose.item.ItemType;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.gildedrose.GildedRoseTestFactory.*;
import static com.gildedrose.item.ItemType.*;
import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {
    private static final Faker faker = new Faker();

    @ParameterizedTest(name = "After {0} days, sellIn should be {1} and quality should be {2}")
    @MethodSource("provideTimesAndExpectedQualityAndSellIn")
    void normalItemUpdateTest(int times, int expectedSellIn, int expectedQuality) {
        // given
        GildedRose gildedRose = createWithItem(faker.funnyName().name(), 10, 20);

        // when
        updateQuality(gildedRose, times);

        // then
        assertThat(gildedRose.items[0].sellIn).isEqualTo(expectedSellIn);
        assertThat(gildedRose.items[0].quality).isEqualTo(expectedQuality);
    }

    @ParameterizedTest(name = "Item: \"{0}\" - After {1} days, sellIn should be {2} and quality should be {3}")
    @MethodSource("provideSpecialItems")
    void specialItemUpdateTest(ItemType itemName, int times, int expectedSellIn, int expectedQuality) {
        // given
        GildedRose gildedRose = createWithItem(itemName, 10, 20);

        // when
        updateQuality(gildedRose, times);

        // then
        assertThat(gildedRose.items[0].sellIn).isEqualTo(expectedSellIn);
        assertThat(gildedRose.items[0].quality).isEqualTo(expectedQuality);
    }

    @Test
    void sulfurasWithNegativeSellInDoesntChange() {
        // given
        GildedRose gildedRose = createWithItem(SULFURAS.getName(), -1, 999);

        // when
        updateQuality(gildedRose, 50);

        // then
        assertThat(gildedRose.items[0].sellIn).isEqualTo(-1);
        assertThat(gildedRose.items[0].quality).isEqualTo(80);
    }

    @ParameterizedTest(name = "After {0} days, sellIn should be {1} and quality should be {2}")
    @MethodSource("provideTimesAndExpectedQualityAndSellInConjured")
    void conjuredItemUpdateTest(int times, int expectedSellIn, int expectedQuality) {
        // given
        GildedRose gildedRose = createWithItem("Conjured " + faker.funnyName().name(), 10, 20);

        // when
        updateQuality(gildedRose, times);

        // then
        assertThat(gildedRose.items[0].sellIn).isEqualTo(expectedSellIn);
        assertThat(gildedRose.items[0].quality).isEqualTo(expectedQuality);
    }

    @Test
    void multipleItemsTest() {
        GildedRose gildedRoseWithMultipleItems = createWithItems(
            new Item(faker.beer().name(), 10, 20),
            new Item(AGED_BRIE.getName(), 10, 20),
            new Item(SULFURAS.getName(), 10, 80),
            new Item(BACKSTAGE_PASSES.getName(), 10, 20),
            new Item("Conjured " + faker.beer().name(), 10, 20)
        );
        gildedRoseWithMultipleItems.updateQuality();


        assertThat(gildedRoseWithMultipleItems.items[0].quality).isEqualTo(19);
        assertThat(gildedRoseWithMultipleItems.items[1].quality).isEqualTo(21);
        assertThat(gildedRoseWithMultipleItems.items[2].quality).isEqualTo(80);
        assertThat(gildedRoseWithMultipleItems.items[3].quality).isEqualTo(22);
        assertThat(gildedRoseWithMultipleItems.items[4].quality).isEqualTo(18);
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

            // Sulfuras never has to be sold or decreases in Quality
            Arguments.of(SULFURAS, 1, 10, 80),
            Arguments.of(SULFURAS, 10, 10, 80),
            Arguments.of(SULFURAS, 100, 10, 80),

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
