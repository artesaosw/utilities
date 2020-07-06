package dev.craftsmanship.utiils.validations;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class DateValidatorBuilder {

    private Optional<NullableValidator> nullableValidator;

    private Optional<Bounds> bounds;

    public DateValidatorBuilder() {
    }

    public DateValidatorBuilder nullable(@NotBlank String nullErrorMessages){
        nullableValidator=Optional.of(new NullableValidator(nullErrorMessages));
        return this;
    }

    public DateValidatorBuilder withBounds(@NotNull Bounds bounds){
        this.bounds = Optional.of(bounds);
        return this;
    }

    public DateValidator build(){

        final DateValidator validator = new DateValidator();

        nullableValidator.ifPresent(value -> validator.nullableValidator = value);
        bounds.ifPresent(value -> validator.bounds = value);

        return validator;
    }
}
