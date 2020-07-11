package dev.craftsmanship.utils.validations;

import dev.craftsmanship.utils.streams.Result;

import java.util.Objects;

public class IntegerValidator implements GenericValidator<Long> {

    NullValidator nullValidator;

    Bounds bounds = Bounds.noBounds(Long.class);

    IntegerValidator() {

    }

    @Override
    public boolean nullValidation(){
        return nullValidator != null;
    };

    @Override
    public NullValidator nullValidator(){
        return nullValidator;
    }

    public boolean boundsValidation(){
        return bounds != null;
    }

    private Result nullValidation(Long value) {
        return GenericValidator.super.validate(value);
    }

    @Override
    public Result validate(Long value) {

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
        if (!(o instanceof IntegerValidator)) return false;
        IntegerValidator that = (IntegerValidator) o;
        return Objects.equals(nullValidator, that.nullValidator) &&
                Objects.equals(bounds, that.bounds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nullValidator, bounds);
    }

    @Override
    public String toString() {
        return "IntegerValidator{" +
                "nullableValidator=" + nullValidator +
                ", bounds=" + bounds +
                '}';
    }
}
