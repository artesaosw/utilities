package dev.craftsmanship.utils.validations;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class DateTimeValidatorBuilder {

    private Optional<NullValidator> nullableValidator;

    private Optional<Bounds> bounds;

    public DateTimeValidatorBuilder() {
    }

    public DateTimeValidatorBuilder nullable(@NotBlank String nullErrorMessages){
        nullableValidator=Optional.of(new NullValidator(nullErrorMessages));
        return this;
    }

    public DateTimeValidatorBuilder withBounds(@NotNull Bounds bounds){
        this.bounds = Optional.of(bounds);
        return this;
    }

    public DateTimeValidator build(){

        final DateTimeValidator validator = new DateTimeValidator();

        nullableValidator.ifPresent(value -> validator.nullValidator = value);
        bounds.ifPresent(value -> validator.bounds = value);

        return validator;
    }
}
