package dev.craftsmanship.utils.streams;

@FunctionalInterface
public interface Expression {
    <T extends Object> T eval();
}
