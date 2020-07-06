package dev.craftsmanship.utiils.validations;

import dev.craftsmanship.utils.streams.Result;

public interface GenericValidator<T extends Object> {

    boolean nullValidation();

    NullableValidator nullValidator();

    default Result validate(T value){
        Result result = Result.positive();
        if(nullValidation()){
            result = nullValidator().validate(value);
        }
        return result;
    }

}
