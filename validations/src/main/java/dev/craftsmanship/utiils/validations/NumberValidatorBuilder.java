package dev.craftsmanship.utiils.validations;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class NumberValidatorBuilder {

    private Optional<NullableValidator> nullableValidator;

    private Optional<Bounds> bounds;

    public NumberValidatorBuilder() {
    }

    public NumberValidatorBuilder nullable(@NotBlank String nullErrorMessages){
        nullableValidator=Optional.of(new NullableValidator(nullErrorMessages));
        return this;
    }

    public NumberValidatorBuilder withBounds(@NotNull Bounds bounds){
        this.bounds = Optional.of(bounds);
        return this;
    }

    public NumberValidator build(){

        final NumberValidator validator = new NumberValidator();

        nullableValidator.ifPresent(value -> validator.nullableValidator = value);
        bounds.ifPresent(value -> validator.bounds = value);

        return validator;
    }
}
