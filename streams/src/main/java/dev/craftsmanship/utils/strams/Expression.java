package dev.craftsmanship.utils.strams;

@FunctionalInterface
public interface Expression {
    <T extends Object> T eval();
}
