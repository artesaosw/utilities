package dev.craftsmanship.utiils.validations;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class DateTimeValidatorBuilder {

    private Optional<NullableValidator> nullableValidator;

    private Optional<Bounds> bounds;

    public DateTimeValidatorBuilder() {
    }

    public DateTimeValidatorBuilder nullable(@NotBlank String nullErrorMessages){
        nullableValidator=Optional.of(new NullableValidator(nullErrorMessages));
        return this;
    }

    public DateTimeValidatorBuilder withBounds(@NotNull Bounds bounds){
        this.bounds = Optional.of(bounds);
        return this;
    }

    public DateTimeValidator build(){

        final DateTimeValidator validator = new DateTimeValidator();

        nullableValidator.ifPresent(value -> validator.nullableValidator = value);
        bounds.ifPresent(value -> validator.bounds = value);

        return validator;
    }
}
