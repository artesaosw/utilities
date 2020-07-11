package dev.craftsmanship.utils.validations;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public enum ValueType {

    STRING, INTEGER, NUMBER, DATE, DATE_TIME;

    public <T extends Comparable> T max(){
        return (T) switch (this){
            case STRING -> Constants.MAX_STRING;
            case INTEGER -> Long.MAX_VALUE;
            case NUMBER -> BigDecimal.valueOf(Double.MAX_VALUE);
            case DATE -> LocalDate.MAX;
            case DATE_TIME -> LocalDateTime.MAX;
        };
    }

    public <T extends Comparable> T min(){
        return (T) switch (this){
            case STRING -> Constants.MIN_STRING;
            case INTEGER -> Long.MIN_VALUE;
            case NUMBER -> BigDecimal.valueOf(Double.MIN_VALUE);
            case DATE -> LocalDate.MIN;
            case DATE_TIME -> LocalDateTime.MIN;
        };
    }

    public Class getTypeClass(){
        return getTypeClass(this);
    }

    public static Class getTypeClass(@NotNull ValueType valueType){
        return switch (valueType){
            case STRING -> String.class;
            case INTEGER -> Long.class;
            case NUMBER -> BigDecimal.class;
            case DATE -> LocalDate.class;
            case DATE_TIME -> LocalDateTime.class;
        };
    }

    public static ValueType fromClass(Class clazz){
        return switch (clazz.getSimpleName()){
            case "String" -> STRING;
            case "Long","Integer","Short","Byte" -> INTEGER;
            case "BigDecimal" -> NUMBER;
            case "LocalDate" -> DATE;
            case "LocalDateTime" -> DATE_TIME;
            default -> throw new IllegalStateException("Unexpected value: " + clazz.getSimpleName());
        };
    }
}
