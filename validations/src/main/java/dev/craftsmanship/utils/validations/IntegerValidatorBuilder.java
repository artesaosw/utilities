package dev.craftsmanship.utils.validations;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class IntegerValidatorBuilder {

    private Optional<NullValidator> nullableValidator;

    private Optional<Bounds> bounds;

    public IntegerValidatorBuilder() {
    }

    public IntegerValidatorBuilder nullable(@NotBlank String nullErrorMessages){
        nullableValidator=Optional.of(new NullValidator(nullErrorMessages));
        return this;
    }

    public IntegerValidatorBuilder withBounds(@NotNull Bounds bounds){
        this.bounds = Optional.of(bounds);
        return this;
    }

    public IntegerValidator build(){

        final IntegerValidator validator = new IntegerValidator();

        nullableValidator.ifPresent(value -> validator.nullValidator = value);
        bounds.ifPresent(value -> validator.bounds = value);

        return validator;
    }

}
