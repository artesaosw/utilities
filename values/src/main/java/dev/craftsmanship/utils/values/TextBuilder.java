package dev.craftsmanship.utils.values;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.regex.Pattern;

import static dev.craftsmanship.utils.errors.Errors.invalidParameter;

public class TextBuilder extends ValueBuilder {

    private static final String UNIVERSAL_PATTERN = "(.*?|[\\s\\S]*?)";

    private Pattern pattern;

    private Range<Integer> sizeRange;

    public TextBuilder(){ }

    public TextBuilder withPattern(@NotBlank String pattern){
        this.pattern = Pattern.compile(pattern);
        return this;
    }

    public TextBuilder withSizeLimits(@PositiveOrZero int min, @Positive int max){

        if (max < min){
            invalidParameter("Invalid limits.");
        }

        this.sizeRange = Range.define(
                RangeBound.define(RangeBoundType.CLOSED, min),
                RangeBound.define(RangeBoundType.CLOSED, max));

        return this;
    }

    public Text build(){

        if (getInvalidValueMessage() == null){
            setInvalidValueMessage(Value.DEFAULT_INVALID_VALUE_MESSAGE);
        }

        if (pattern == null){
            pattern = Pattern.compile(UNIVERSAL_PATTERN);
        }

        if (sizeRange == null){
            sizeRange = Range.define(
                    RangeBound.min(String.class),
                    RangeBound.max(String.class));
        }

        return new Text(getInvalidValueMessage(), pattern, sizeRange);
    }
}
