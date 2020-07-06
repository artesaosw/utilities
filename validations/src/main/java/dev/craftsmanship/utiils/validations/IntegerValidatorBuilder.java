package dev.craftsmanship.utiils.validations;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class IntegerValidatorBuilder {

    private Optional<NullableValidator> nullableValidator;

    private Optional<Bounds> bounds;

    public IntegerValidatorBuilder() {
    }

    public IntegerValidatorBuilder nullable(@NotBlank String nullErrorMessages){
        nullableValidator=Optional.of(new NullableValidator(nullErrorMessages));
        return this;
    }

    public IntegerValidatorBuilder withBounds(@NotNull Bounds bounds){
        this.bounds = Optional.of(bounds);
        return this;
    }

    public IntegerValidator build(){

        final IntegerValidator validator = new IntegerValidator();

        nullableValidator.ifPresent(value -> validator.nullableValidator = value);
        bounds.ifPresent(value -> validator.bounds = value);

        return validator;
    }

}
