package dev.craftsmanship.utils.validations;

import dev.craftsmanship.utils.streams.Result;

import java.util.Objects;
import java.util.regex.Pattern;

public class StringValidator implements GenericValidator<String> {

    private static final String UNIVERSAL_PATTERN = "(.*?|[\\s\\S]*?)";

    private static final String DEFAULT_REGEX_ERROR_MESSAGE = "Value should match defined regular expression.";
    private static final String DEFAULT_NULL_ERROR_MESSAGE = "Value should not be null.";

    String nullErrorMessage = DEFAULT_NULL_ERROR_MESSAGE;

    NullValidator nullValidator;

    Bounds bounds = Bounds.noBounds(Long.class);

    Pattern pattern = Pattern.compile(UNIVERSAL_PATTERN);

    String regexErrorMesage = DEFAULT_REGEX_ERROR_MESSAGE;

    StringValidator() {

    }

    public boolean nullValidation(){
        return nullValidator != null;
    };

    public NullValidator nullValidator(){
        return nullValidator;
    }

    public boolean boundsValidation(){
        return bounds != null;
    }

    public boolean regexValidation(){
        return pattern != null;
    };

    private Result nullValidation(String value) {
        return GenericValidator.super.validate(value);
    }

    @Override
    public Result validate(String value) {

        boolean success = true;
        StringBuffer errorMsgs = new StringBuffer();

        Result result = nullValidation(value);
        success = success && result.sucesso();
        errorMsgs.append(result.mensagem());

        if (boundsValidation()){
            result = bounds.validate(value.trim().length());
            success = success && result.sucesso();
        }

        if (regexValidation()){
            if (!pattern.matcher(value).matches()){
                success = success && false;
                errorMsgs.append(regexErrorMesage);
            }
        }

        return success ?
                Result.positive() :
                Result.negative(errorMsgs.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof StringValidator)) return false;
        StringValidator that = (StringValidator) o;
        return Objects.equals(nullErrorMessage, that.nullErrorMessage) &&
                Objects.equals(nullValidator, that.nullValidator) &&
                Objects.equals(bounds, that.bounds) &&
                Objects.equals(pattern, that.pattern) &&
                Objects.equals(regexErrorMesage, that.regexErrorMesage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nullErrorMessage, nullValidator, bounds, pattern, regexErrorMesage);
    }
}
