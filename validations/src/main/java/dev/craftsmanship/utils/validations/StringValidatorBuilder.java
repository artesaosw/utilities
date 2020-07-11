package dev.craftsmanship.utils.validations;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.regex.Pattern;

public class StringValidatorBuilder {

    private Optional<NullValidator> nullableValidator;

    private Optional<Bounds> bounds;

    private Optional<Pattern> regexPattern;

    private Optional<String> regexErrorMessage;

    public StringValidatorBuilder() {
        nullableValidator= Optional.empty();
        bounds = Optional.empty();
        regexPattern = Optional.empty();
    }

    public StringValidatorBuilder notNull(@NotBlank String nullErrorMessages){
        nullableValidator=Optional.of(new NullValidator(nullErrorMessages));
        return this;
    }

    public StringValidatorBuilder withBounds(@NotNull Bounds bounds){
        this.bounds = Optional.of(bounds);
        return this;
    }

    public StringValidatorBuilder withRegex(@NotBlank String pattern, @NotBlank String errorMessage){
        this.regexPattern = Optional.of(Pattern.compile(pattern));
        this.regexErrorMessage = Optional.of(errorMessage);
        return this;
    }

    public StringValidator build(){
        StringValidator ret = new StringValidator();

        nullableValidator.ifPresent(
                value -> {ret.nullValidator = value;}
        );

        bounds.ifPresent(
                value -> {ret.bounds = value;}
        );

        regexPattern.ifPresent(
                value -> {ret.pattern = value;}
        );

        return ret;
    }
}
