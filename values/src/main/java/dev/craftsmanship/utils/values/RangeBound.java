package dev.craftsmanship.utils.values;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

enum ValueType {

    STRING, INTEGER, NUMBER, DATE, DATE_TIME;

    public <T extends Comparable> T max(){
        return (T) switch (this){
            case STRING -> Constants.MAX_STRING;
            case INTEGER -> Long.MAX_VALUE;
            case NUMBER -> BigDecimal.valueOf(Double.MAX_VALUE);
            case DATE -> LocalDate.MAX;
            case DATE_TIME -> LocalDateTime.MAX;
        };
    }

    public <T extends Comparable> T min(){
        return (T) switch (this){
            case STRING -> Constants.MIN_STRING;
            case INTEGER -> Long.MIN_VALUE;
            case NUMBER -> BigDecimal.valueOf(Double.MIN_VALUE);
            case DATE -> LocalDate.MIN;
            case DATE_TIME -> LocalDateTime.MIN;
        };
    }

    public Class getTypeClass(){
        return getTypeClass(this);
    }

    public static Class getTypeClass(@NotNull ValueType valueType){
        return switch (valueType){
            case STRING -> String.class;
            case INTEGER -> Long.class;
            case NUMBER -> BigDecimal.class;
            case DATE -> LocalDate.class;
            case DATE_TIME -> LocalDateTime.class;
        };
    }

    public static ValueType fromClass(Class clazz){
        return switch (clazz.getSimpleName()){
            case "String" -> STRING;
            case "Long","Integer","Short","Byte" -> INTEGER;
            case "BigDecimal" -> NUMBER;
            case "LocalDate" -> DATE;
            case "LocalDateTime" -> DATE_TIME;
            default -> throw new IllegalStateException("Unexpected value: " + clazz.getSimpleName());
        };
    }
}

@Getter
public class RangeBound<T extends Comparable>{

    private RangeBoundType boundType;

    private T bound;

    RangeBound(@NotNull RangeBoundType boundType, @NotNull T bound) {
        this.boundType = boundType;
        this.bound = bound;
    }

    public ValueType valueType(){
        return valueType(bound.getClass());
    }

    private static ValueType valueType(@NotNull Class clazz){
        return ValueType.fromClass(clazz);
    }

    public static <T extends Comparable> RangeBound define(@NotNull RangeBoundType boundType, @NotNull T bound){
        return new RangeBound(
                boundType,
                bound);
    }

    public static RangeBound min(@NotNull Class clazz) {
        return new RangeBound(
                RangeBoundType.OPENED,
                valueType(clazz).min());
    }

    public static RangeBound min(ValueType valueType) {
        return min(ValueType.getTypeClass(valueType));
    }

    public static RangeBound max(@NotNull Class clazz) {
        return new RangeBound(
                RangeBoundType.OPENED,
                valueType(clazz).max());
    }

    public static RangeBound max(ValueType valueType) {
        return max(ValueType.getTypeClass(valueType));
    }

    public static RangeBound zero() {
        return new RangeBound(
                RangeBoundType.OPENED,0);
    }

    public boolean opened() {
        return boundType.equals(RangeBoundType.OPENED);
    }

    public boolean closed() {
        return !opened();
    }

    public boolean matchesUpperBound(@NotNull T value){
        int compareResult = value.compareTo(bound);
        return switch (boundType) {
            case OPENED -> compareResult < 0;
            case CLOSED -> compareResult <= 0;
        };
    }

    public boolean matchesLowerBound(@NotNull T value){
        int compareResult = value.compareTo(bound);
        return switch (boundType) {
            case OPENED -> compareResult > 0;
            case CLOSED -> compareResult >= 0;
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RangeBound)) return false;
        RangeBound that = (RangeBound) o;
        return bound == that.bound &&
                boundType == that.boundType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(boundType, bound);
    }

    @Override
    public String toString() {
        return "RangeBound{" +
                "boundType=" + boundType +
                ", bound=" + bound +
                '}';
    }
}
