package dev.craftsmanship.utils.streams;

@FunctionalInterface
public interface Operation {
    Result proceed();
}
