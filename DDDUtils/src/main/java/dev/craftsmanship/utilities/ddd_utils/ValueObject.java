package dev.craftsmanship.utilities.ddd_utils;

import dev.craftsmanship.utils.errors.UnexpectedErrorException;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public interface ValueObject extends DomainObject {

    private boolean equals(Field field, Object o1, Object o2){
        try {
            return field.get(o1).equals(field.get(o2));
        } catch (IllegalAccessException e) {
            throw new UnexpectedErrorException(e.getMessage(), e);
        }
    }

    private boolean compareAttributes(List<Field> fields, Object o1, Object o2){
        final Boolean[] result = new Boolean[]{true};
        fields.forEach(field -> {
            field.setAccessible(true);
            if (!equals(field, o1, o2)){
                result[0] = false;
            }
        });
        return result[0];
    }

    private static int hashCodeAttributes(List<Field> fields, DomainObject domainObject){
        final int[] result = new int[]{1};
        fields.forEach(field -> {
            try {
                result[0] = result[0] * field.get(domainObject).hashCode();
            } catch (IllegalAccessException e) {
                throw new UnexpectedErrorException(e.getMessage(),e);
            }
        });
        return result[0];
    }

    public default <T extends DomainObject> boolean equals(T other) {
        if (other == null){
            return false;
        }
        if (!getClass().isAssignableFrom(other.getClass())){
            return false;
        }
        boolean result = true;
        Class clazz = other.getClass();
        while (!clazz.equals(Object.class)){
            List<Field> fields = Arrays.asList(clazz.getFields());
            if (!compareAttributes(fields,this,other)){
                return false;
            }
            clazz = clazz.getSuperclass();
        }
        return result;
    }

    public static int hashCode(@NotNull DomainObject domainObject){
        int result = 1;
        Class clazz = domainObject.getClass();
        while (!clazz.equals(Object.class)){
            List<Field> fields = Arrays.asList(clazz.getFields());
            result = result * hashCodeAttributes(fields, domainObject);
            clazz = clazz.getSuperclass();
        }
        return result;
    }


}
