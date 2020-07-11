package dev.craftsmanship.utils.validations;

import dev.craftsmanship.utils.streams.Result;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public abstract class CustomValidator<T extends Comparable> implements GenericValidator<T>{

    private NullValidator nullValidator;

    /**
     * Default constructor: no not null validation
     */
    protected CustomValidator() { }

    /**
     * With 1 String parameter: not null validation
     * @param nullErrorMessage
     */
    protected CustomValidator(@NotBlank String nullErrorMessage) {
        this.nullValidator = new NullValidator(nullErrorMessage);
    }

    @Override
    public boolean nullValidation() {
        return nullValidator != null;
    }

    @Override
    public NullValidator nullValidator() {
        return nullValidator;
    }

    @Override
    public Result validate(T value) {
        return nullValidation() ?
                nullValidator.validate(value) :
                Result.positive();
    }
}
