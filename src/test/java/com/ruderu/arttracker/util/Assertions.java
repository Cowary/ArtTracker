package com.ruderu.arttracker.util;

import org.springframework.util.Assert;

import java.util.Objects;
import java.util.function.Predicate;

public class Assertions {

    public <V> Assertions condition(String assertionName, V value, Predicate<V> condition) {
        Assert.isTrue(
                condition.test(value),
                "Проверка не пройдена: " + assertionName);
        return this;
    }

    public Assertions isTrue(String assertionName, boolean condition) {
        Assert.isTrue(
                condition,
                "Проверка не пройдена: " + assertionName);
        return this;
    }

    public <V> Assertions equals(String assertionName, V expectedValue, V actualValue) {
        Assert.isTrue(
                Objects.equals(expectedValue, actualValue),
                "Проверка не пройдена: " + assertionName + ".\n" +
                        "Ожидаемое значение: " + expectedValue + ".\n" +
                        "Фактическое значение: " + actualValue
        );
        return this;
    }

    public Assertions contains(String assertionName, String expectedContained, String container) {
        Assert.isTrue(
                container.contains(expectedContained),
                "Проверка не пройдена: " + assertionName + ".\n" +
                        "Ожидаемое значение содержит: " + expectedContained + ".\n" +
                        "Фактическое значение: " + container
        );
        return this;
    }

    public <V> Assertions notNull(String assertionName, V value) {
        Assert.notNull(value,
                "Проверка на null не пройдена: " + assertionName);
        return this;
    }
}
