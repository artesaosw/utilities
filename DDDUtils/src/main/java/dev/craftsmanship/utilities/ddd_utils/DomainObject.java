package dev.craftsmanship.utilities.ddd_utils;

import com.google.common.collect.Maps;
import dev.craftsmanship.utils.validations.GenericValidator;
import dev.craftsmanship.utils.streams.Result;
import dev.craftsmanship.utils.validations.Validatable;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static dev.craftsmanship.utils.errors.Errors.*;

public abstract class DomainObject implements Validatable {

    @NotNull
    public abstract <T extends GenericValidator> Map<String, Collection<T>> validators();

    private Collection<GenericValidator> validators(Field field) {
        return validators().get(field.getName());
    }

    private <T extends GenericValidator> Result validateField(final Field field, Collection<T> validators){
        Boolean result[] = new Boolean[]{true};
        StringBuffer buffer = new StringBuffer();
        validators.forEach(validator -> {
             try {
                 Result currentResult = validator.validate(field.get(this));
                 result[0] = result[0] && currentResult.sucesso();
                 if (!currentResult.sucesso()){
                     buffer.append(currentResult.mensagem());
                 }
             } catch (IllegalAccessException e) {
                 undefinedError(e.getMessage(), e);
             }
         });
        return result[0] ?
                Result.positive() :
                Result.negative(
                        buffer.toString());
    }

    public Map<String, Result> validate(){
        Map<String,Result> fieldValidations = Maps.newHashMap();
        Class clazz = getClass();
        while (!clazz.equals(Entity.class) && !clazz.equals(ValueObject.class)){
            Arrays.asList(clazz.getFields()).forEach(
                    field -> {
                        field.setAccessible(true);
                        fieldValidations.put(
                                field.getName(),
                                validateField(field,validators(field)));
                    });
            clazz = clazz.getSuperclass();
        }
        return fieldValidations;
    }

}
