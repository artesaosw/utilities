package dev.craftsmanship.utils.validations;

import dev.craftsmanship.utils.streams.Result;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTimeValidator implements GenericValidator<LocalDateTime> {

    NullValidator nullValidator;

    Bounds bounds = Bounds.noBounds(LocalDate.class);

    public DateTimeValidator() {

    }

    @Override
    public boolean nullValidation() {
        return false;
    }

    @Override
    public NullValidator nullValidator() {
        return null;
    }

    public boolean boundsValidation(){
        return bounds != null;
    }

    private Result nullValidation(LocalDateTime value) {
        return GenericValidator.super.validate(value);
    }

    @Override
    public Result validate(LocalDateTime value) {
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
