package dev.craftsmanship.utils.values;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
public class Range<T extends Comparable> {

    private RangeBound lower;
    private RangeBound upper;

    Range(){}

    public ValueType valueClass(){
        return lower.valueType();
    }
    
    public static Range define(@NotNull RangeBound lower, @NotNull RangeBound upper){
        Range range = new Range();
        range.lower = lower;
        range.upper = upper;
        return range;
    }

    static Range low(@NotNull RangeBound lower){
        Range range = new Range();
        range.lower = lower;
        range.upper = RangeBound.max(lower.valueType());
        return range;
    }

    static Range up(@NotNull RangeBound upper){
        Range range = new Range();
        range.lower = RangeBound.min(upper.valueType());
        range.upper = upper;
        return range;
    }

    static Range noRange(@NotNull ValueType valueType){
        Range range = new Range();
        range.lower = RangeBound.min(valueType);
        range.upper = RangeBound.max(valueType);
        return range;
    }

    public boolean validate(@NotNull T value) {
        return lower.matchesLowerBound(value) &&
                upper.matchesUpperBound(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Range)) return false;
        Range range = (Range) o;
        return lower.equals(range.lower) &&
                upper.equals(range.upper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lower, upper);
    }
}
