package dev.craftsmanship.utils.values;

import com.google.common.collect.Maps;
import dev.craftsmanship.utiils.validations.GenericValidator;
import dev.craftsmanship.utiils.validations.Validatable;
import dev.craftsmanship.utils.streams.Result;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

import static dev.craftsmanship.utils.errors.Errors.invalidParameter;
import static dev.craftsmanship.utils.errors.Errors.undefinedError;


@Getter
public abstract  class Value<T extends Comparable, V extends GenericValidator> implements Comparable<T>, Validatable ,Serializable {

    static final String VALUE_FIELD_NAME = "internalValue";
    static final String DEFAULT_INVALID_VALUE_MESSAGE = "Invalid value.";

    @Setter(AccessLevel.PACKAGE)
    private T internalValue;

    @Setter(AccessLevel.PACKAGE)
    private String invalidMessage;

    private Collection<V> validators =  new HashSet<>();


    @Override
    public Map<String, Collection<V>> validators() {
        return Map.of(VALUE_FIELD_NAME, validators);
    }

    public void defineValidators(@NotNull @NotEmpty Collection<V> validators){
        this.validators = validators;
    }

    public void removeValidators(){
        validators.clear();
    }

    @Override
    public Map<String, Result> validate() {
        Boolean result[] = new Boolean[]{true};
        StringBuffer buffer = new StringBuffer();
        validators.forEach(
                validator -> {
                    Result currentResult = validator.validate(internalValue);
                    result[0] = result[0] && currentResult.sucesso();
                    if (!currentResult.sucesso()){
                        buffer.append(currentResult.mensagem());
                    }
                });
        return Map.of(
                VALUE_FIELD_NAME,
                result[0] ?
                Result.positive() :
                Result.negative(
                        buffer.toString()));
    }

    public final boolean hasValue() {
        return getInternalValue() != null;
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
        Value value = (Value) o;
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

    @Override
    public int compareTo(T o) {
        if (internalValue != null && o != null){
            return internalValue.compareTo(o);
        } else if (internalValue == null) {
            return -1;
        }
        return 1;
    }
}
