package dev.craftsmanship.utiils.validations;

import dev.craftsmanship.utils.streams.Result;

import java.time.LocalDate;

public class DateValidator implements GenericValidator<LocalDate> {

    NullableValidator nullableValidator;

    Bounds bounds = Bounds.noBounds(LocalDate.class);

    public DateValidator() {

    }

    @Override
    public boolean nullValidation() {
        return false;
    }

    @Override
    public NullableValidator nullValidator() {
        return null;
    }

    public boolean boundsValidation(){
        return bounds != null;
    }

    private Result nullValidation(LocalDate value) {
        return GenericValidator.super.validate(value);
    }

    @Override
    public Result validate(LocalDate value) {

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
}
