package com.tonmoy.bartender.enumeration;

import java.util.Objects;

public enum DrinkType {
    BEER('B', "BEER"),
    DRINK('D', "DRINK");

    private final Character value;
    private final String text;

    DrinkType(Character value, String text) {
        this.value = value;
        this.text = text;
    }

    public static String find(Character value) {
        for (DrinkType drinkType : values()) {
            if (Objects.equals(value, drinkType.value())) {
                return drinkType.text;
            }

        }
        return null;
    }

    public Character value() {
        return value;
    }

    public String text() {
        return text;
    }
}
