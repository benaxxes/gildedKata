package com.gildedrose.item;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemFactory {
    private static final String CONJURED_PREFIX = "Conjured";

    public static ItemUpdater newItem(Item item) {
        //todo: validate item

        return Arrays.stream(ItemType.values())
            .filter(type -> type.getName().equals(item.name))
            .findFirst()
            .map(type -> mapItem(item, type))
            .orElseGet(() -> {
                if (isConjured(item)) {
                    return new ConjuredItem(item);
                }
                return new NormalItem(item);
            });
    }

    private static ItemUpdater mapItem(Item item, ItemType type) {
        return switch (type) {
            case AGED_BRIE -> new AgedBrie(item);
            case BACKSTAGE_PASSES -> new BackstagePass(item);
            case SULFURAS -> new Sulfuras(item);
        };
    }

    private static boolean isConjured(Item item) {
        return item.name.startsWith(CONJURED_PREFIX);
    }
}
