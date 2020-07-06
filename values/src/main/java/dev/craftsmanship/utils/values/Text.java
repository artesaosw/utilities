package dev.craftsmanship.utils.values;

import dev.craftsmanship.utiils.validations.GenericValidator;
import dev.craftsmanship.utils.streams.Result;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Map;
import java.util.regex.Pattern;

import static dev.craftsmanship.utils.errors.Errors.invalidParameter;

@Embeddable
@Getter
public class Text<V extends GenericValidator> extends Value<String,V> {

    public static final String VALUE_DOES_NOT_MATCH_PATTERN = "Value does not match pattern.";
    private Pattern pattern;

    private Range<Integer> sizeRange;

    Text(){}

    Text(String invalidValueMessage, Pattern pattern, Range<Integer> sizeRange) {
        this.pattern = pattern;
        this.sizeRange = sizeRange;
        setInvalidMessage(invalidValueMessage);
    }

    private void validateRegex() {
        if (hasValue() && hasPattern() && !matchesPattern()){
            invalidParameter(VALUE_DOES_NOT_MATCH_PATTERN);
        }
    }

    public final boolean matchesPattern() {
        return pattern
                    .matcher(getInternalValue())
                    .matches();
    }

    public final boolean hasPattern(){
        return pattern != null;
    }

    @Override
    public Map<String, Result> validate(){
        Map<String, Result> result = super.validate();
        if(hasPattern()){
            result.put(
                    VALUE_FIELD_NAME,
                    matchesPattern() ?
                            Result.positive() :
                            Result.negative(VALUE_DOES_NOT_MATCH_PATTERN));
        }
        return result;
    }
}

