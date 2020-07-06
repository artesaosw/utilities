package dev.craftsmanship.utiils.validations;

import dev.craftsmanship.utils.streams.Result;

import java.math.BigDecimal;
import java.util.Objects;

public class NumberValidator implements GenericValidator<BigDecimal> {

    NullableValidator nullableValidator;

    Bounds bounds = Bounds.noBounds(BigDecimal.class);

    NumberValidator() {
    }

    public boolean nullValidation(){
        return nullableValidator != null;
    };

    public NullableValidator nullValidator(){
        return nullableValidator;
    }

    public boolean boundsValidation(){
        return bounds != null;
    }

    private Result nullValidation(BigDecimal value) {
        return GenericValidator.super.validate(value);
    }

    @Override
    public Result validate(BigDecimal value) {

        boolean success = true;
        StringBuffer errorMsgs = new StringBuffer();

        Result result = nullValidation(value);
        success = success && result.sucesso();
        errorMsgs.append(result.mensagem());

        if (boundsValidation()){
            result = bounds.validate(value);
            if (!result.sucesso()){
                success = success && false;
                errorMsgs.append(result.mensagem());
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
        if (!(o instanceof NumberValidator)) return false;
        NumberValidator that = (NumberValidator) o;
        return Objects.equals(nullableValidator, that.nullableValidator) &&
                Objects.equals(bounds, that.bounds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nullableValidator, bounds);
    }
}
