package dev.craftsmanship.utiils.validations;

import dev.craftsmanship.utils.streams.Result;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Objects;

import static dev.craftsmanship.utils.errors.Errors.invalidParameter;

public class Bounds<T extends Comparable> {

    private static final String DEFAULT_ERROR_MESSAGE = "Bound validation error.";

    private String errorMessage;

    private T max;
    private T min;

    public Bounds(@NotNull T min, @NotNull T max, @NotBlank String errorMessage) {
        if (max.compareTo(min) < 0){
            invalidParameter(errorMessage);
        }
        this.min = min;
        this.max = max;
        this.errorMessage = errorMessage;
    }

    public Result validate(@NotNull T value){
        return (value.compareTo(min) >= 0 && value.compareTo(max) <= 0) ?
                Result.positive() :
                Result.negative(errorMessage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bounds)) return false;
        Bounds<?> bounds = (Bounds<?>) o;
        return Objects.equals(errorMessage, bounds.errorMessage) &&
                Objects.equals(max, bounds.max) &&
                Objects.equals(min, bounds.min);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorMessage, max, min);
    }

    @Override
    public String toString() {
        return "Bounds{" +
                "errorMessage='" + errorMessage + '\'' +
                ", max=" + max +
                ", min=" + min +
                '}';
    }

    public static Bounds noBounds(Class clazz) {
        return new Bounds(
                ValueType.fromClass(clazz).min(),
                ValueType.fromClass(clazz).max(),
                DEFAULT_ERROR_MESSAGE);
    }
}
