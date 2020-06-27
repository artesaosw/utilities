package dev.craftsmanship.utils.values;

import dev.craftsmanship.utils.values.Range;
import dev.craftsmanship.utils.values.Value;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

import static dev.craftsmanship.utils.errors.Errors.invalidParameter;

@Embeddable
@Getter
public class Text extends Value<String> {

    private Pattern pattern;

    private Range<Integer> sizeRange;

    Text(boolean nullable, Range bounds, String invalidValueMessage, Pattern pattern, Range<Integer> sizeRange) {
        super(nullable, bounds, invalidValueMessage);
        this.pattern = pattern;
        this.sizeRange = sizeRange;
    }

    private void validateRegex() {
        if (hasValue() && hasPattern() && !matchesPattern()){
            invalidParameter("Value does not match pattern.");
        }
    }

    public final boolean matchesPattern() {
        return pattern != null &&
                pattern
                        .matcher(getInternalValue())
                        .matches();
    }

    public final boolean hasPattern(){
        return pattern != null;
    }

    @Override
    protected void customValidate() {
        validateRegex();
    }
}

