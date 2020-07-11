package dev.craftsmanship.utils.validations;

import dev.craftsmanship.utils.streams.Result;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class NullValidator implements GenericValidator<Object> {

    private String errorMessage;

    public NullValidator(@NotBlank String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean nullValidation() {
        return true;
    }

    @Override
    public NullValidator nullValidator() {
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
        if (!(o instanceof NullValidator)) return false;
        NullValidator that = (NullValidator) o;
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
