package com.gildedrose.item;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemFactory {
    private static final String CONJURED_PREFIX = "Conjured";

    public static ItemUpdater newItem(Item item) {
        validate(item);

        return Arrays.stream(ItemType.values())
            .filter(type -> type.getName().equals(item.name))
            .findFirst()
            .map(type -> type.create(item))
            .orElseGet(() -> isConjured(item) ? new ConjuredItem(item) : new NormalItem(item));
    }

    private static void validate(Item item) {
        Objects.requireNonNull(item, "Item cannot be null");
        Objects.requireNonNull(item.name, "Item name cannot be null");
    }

    private static boolean isConjured(Item item) {
        return item.name.startsWith(CONJURED_PREFIX);
    }

}
