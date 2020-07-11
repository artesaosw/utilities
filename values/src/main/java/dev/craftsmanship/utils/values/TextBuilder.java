package dev.craftsmanship.utils.values;

import com.google.common.collect.Sets;
import dev.craftsmanship.utils.errors.UndefinedErrorException;
import dev.craftsmanship.utils.validations.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Set;

import static dev.craftsmanship.utils.errors.Errors.invalidParameter;
import static dev.craftsmanship.utils.errors.Errors.undefinedError;

public class TextBuilder extends ValueBuilder {

    private static final String UNIVERSAL_PATTERN = "(.*?|[\\s\\S]*?)";

    private boolean notNull = false;

    private String nullErrorMessage;

    private String pattern;

    private String patternErrorMessage;

    private Bounds<Long> bounds;

    private String lengthErrorMessage;

    public TextBuilder(){ }

    public TextBuilder notNull(@NotBlank String errorMessage){
        this.notNull = true;
        this.nullErrorMessage = errorMessage;
        return this;
    }

    public TextBuilder withPattern(@NotBlank String pattern, @NotBlank String errorMessage){
        this.pattern = pattern;
        this.patternErrorMessage = errorMessage;
        return this;
    }

    public TextBuilder withSizeLimits(@NotNull Long min, @NotNull Long max, @NotBlank String errorMessage){

        if (max < min){
            invalidParameter("Invalid limits.");
        }

        this.bounds = new Bounds<Long>(min, max, errorMessage);
        this.lengthErrorMessage = errorMessage;
        return this;
    }

    private void handleDefaultAttributes() {
        if (getInvalidValueMessage() == null){
            setInvalidValueMessage(Value.DEFAULT_INVALID_VALUE_MESSAGE);
        }

        if (pattern == null){
            pattern = UNIVERSAL_PATTERN;
        }
    }

    public Text build(){
        return build(Text.class);
    }

    public <T extends Text> T build(@NotNull Class clazz){
        handleDefaultAttributes();
        T text = instance(clazz);
        text.defineValidators(Set.of(defineValidator()));
        return text;
    }

    private StringValidator defineValidator() {
        Collection<? extends GenericValidator> validators = Sets.newHashSet();

        StringValidatorBuilder builder = new StringValidatorBuilder();
        if (!notNull){
            builder.notNull(nullErrorMessage);
        }
        if (hasBounds()){
            builder.withBounds(bounds);
        }
        if (hasPattern()){
            builder.withRegex(pattern,patternErrorMessage);
        }
       return builder.build();
    }

    private <V extends GenericValidator> V regexValidator() {
        return null;
    }

    private boolean hasPattern() {
        return pattern != null;
    }

    private boolean hasBounds() {
        return bounds != null;
    }

    private <V extends GenericValidator> V lengthValidator(Bounds<Long> bounds) {
        IntegerValidatorBuilder builder = new IntegerValidatorBuilder();
        builder.withBounds(bounds);
        return null;
    }

    private <T extends Text> T instance(@NotNull Class clazz){
        if (!Text.class.isAssignableFrom(clazz)){
            invalidParameter("Parameter clazz should extend Text.");
        }
        try {
            return (T) clazz
                    .getDeclaredConstructor(String.class)
                    .newInstance(getInvalidValueMessage());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                NoSuchMethodException e) {
            throw new UndefinedErrorException(e.getMessage());
        }
    }
}
