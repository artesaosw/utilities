package dev.craftsmanship.utils.values;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

import static dev.craftsmanship.utils.errors.Errors.invalidParameter;
import static dev.craftsmanship.utils.errors.Errors.undefinedError;


@Getter
public abstract  class Value<T extends Comparable> implements Comparable<T>, Serializable {

    public static final String DEFAULT_INVALID_VALUE_MESSAGE = "Invalid value.";

    @Setter(AccessLevel.PACKAGE)
    private T internalValue;

    private boolean nullable;

    private Range bounds;

    private String invalidMessage;

    public Value(boolean nullable, Range bounds, String invalidValueMessage) {
        this.nullable = nullable;
        this.bounds = bounds;
        this.invalidMessage = invalidValueMessage;
    }

    protected boolean validateBounds() {
        return bounded() ?
                getBounds().validate(getInternalValue()) :
                true;

    }

    protected void customValidate(){ }

    private boolean violatesNullableConstraint() {
        return !nullable && internalValue == null;
    }

    private void validate() {

        if (violatesNullableConstraint()){
            invalidParameter("Null values aren't acceptable in a non-nullable value.");
        }

        if (!validateBounds()){
            invalidParameter("Value does not match defined bounds.");
        };

        customValidate();
    }

    private void initialize(boolean nullable, @NotBlank String invalidMessage){
        this.nullable = nullable;
        this.invalidMessage = invalidMessage;
    }

    public final boolean bounded(){
        return bounds != null;
    }

    public final boolean hasValue() {
        return getInternalValue() != null;
    }

    @Override
    public int compareTo(T o) {
        if (internalValue != null && o != null){
            return internalValue.compareTo(o);
        } else if (internalValue == null) {
            return -1;
        }
        return 1;
    }

    public void updateValue(T value){
        T oldValue = internalValue;
        try {
            internalValue = value;
            validate();
        } catch(Throwable t){
            internalValue = oldValue;
            undefinedError(invalidMessage);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Value)) return false;
        Value<?> value = (Value<?>) o;
        return Objects.equals(internalValue, value.internalValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalValue);
    }

    @Override
    public String toString() {
        return "Value{" +
                "internalValue=" + internalValue +
                '}';
    }
}
