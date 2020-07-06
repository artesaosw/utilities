package dev.craftsmanship.utiils.validations;

import dev.craftsmanship.utils.streams.Result;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class NullableValidator implements GenericValidator<Object> {

    private String errorMessage;

    public NullableValidator(@NotBlank String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean nullValidation() {
        return true;
    }

    @Override
    public NullableValidator nullValidator() {
        return this;
    }

    @Override
    public Result validate(Object value) {
        return value != null ?
                Result.positive() :
                Result.negative(errorMessage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NullableValidator)) return false;
        NullableValidator that = (NullableValidator) o;
        return Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorMessage);
    }

    @Override
    public String toString() {
        return "NullableValidator{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
