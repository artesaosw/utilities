package dev.craftsmanship.utils.errors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

public class Errors {

    private static  Class DEFAULT_INSTANCE = ExceptionReporter.class;

    private static ErrorReporter instance;

    public static void setInstance(@NotNull ErrorReporter reporter){
        instance = reporter;
    }

    public static ErrorReporter instance(){
        if (instance == null) {
            try {
                instance = (ErrorReporter) DEFAULT_INSTANCE
                        .getDeclaredConstructor(Null.class)
                        .newInstance();
            } catch (NoSuchMethodException |
                    InstantiationException |
                    IllegalAccessException |
                    InvocationTargetException e) {
                throw new UnexpectedErrorException(e.getMessage(), e);
            }
        }
        return instance;
    }

    public static void invalidParameter(@NotBlank String message){
        instance().invalidParameter(message);
    }

    public static <T extends Throwable> void invalidParameter(@NotBlank String message, @NotNull T exceptionCause){
        instance().invalidParameter(message,exceptionCause);
    }

    public static <S extends Serializable> void invalidParameter(@NotBlank String message, @NotNull S additionalData){
        instance().invalidParameter(message,additionalData);
    }

    public static <T extends Throwable, S extends Serializable> void invalidParameter(@NotBlank String message, @NotNull T exceptionCause, @NotNull S additionalData){
        instance().invalidParameter(message,exceptionCause,additionalData);
    }

    public static void invalidOperation(@NotBlank String message){
        instance().invalidOperation(message);
    }

    public static <T extends Throwable> void invalidOperation(@NotBlank String message, @NotNull T exceptionCause){
        instance().invalidOperation(message,exceptionCause);
    }

    public static <S extends Serializable> void invalidOperation(@NotBlank String message, @NotNull S additionalData){
        instance().invalidOperation(message,additionalData);
    }

    public static <T extends Throwable, S extends Serializable> void invalidOperation(@NotBlank String message, @NotNull T exceptionCause, @NotNull S additionalData){
        instance().invalidOperation(message,exceptionCause,additionalData);
    }

    public static void inconsistentInformation(@NotBlank String message){
        instance().inconsistentInformation(message);
    }

    public static <T extends Throwable> void inconsistentInformation(@NotBlank String message, @NotNull T exceptionCause){
        instance().inconsistentInformation(message,exceptionCause);
    }

    public static <S extends Serializable> void inconsistentInformation(@NotBlank String message, @NotNull S additionalData){
        instance().inconsistentInformation(message,additionalData);
    }

    public static <T extends Throwable, S extends Serializable> void inconsistentInformation(@NotBlank String message, @NotNull T exceptionCause, @NotNull S additionalData){
        instance().inconsistentInformation(message,exceptionCause,additionalData);
    }

    public static void inconsistentState(@NotBlank String message){
        instance().inconsistentState(message);
    }

    public static <T extends Throwable> void inconsistentState(@NotBlank String message, @NotNull T exceptionCause){
        instance().inconsistentState(message,exceptionCause);
    }

    public static <S extends Serializable> void inconsistentState(@NotBlank String message, @NotNull S additionalData){
        instance().inconsistentState(message,additionalData);
    }

    public static <T extends Throwable, S extends Serializable> void inconsistentState(@NotBlank String message, @NotNull T exceptionCause, @NotNull S additionalData){
        instance().inconsistentState(message,exceptionCause,additionalData);
    }

    public static void undefinedError(@NotBlank String message){
        instance().undefinedError(message);
    }

    public static <T extends Throwable> void undefinedError(@NotBlank String message, @NotNull T exceptionCause){
        instance().undefinedError(message,exceptionCause);
    }

    public static <S extends Serializable> void undefinedError(@NotBlank String message, @NotNull S additionalData){
        instance().undefinedError(message,additionalData);
    }

    public static <T extends Throwable, S extends Serializable> void undefinedError(@NotBlank String message, @NotNull T exceptionCause, @NotNull S additionalData){
        instance().undefinedError(message,exceptionCause,additionalData);
    }
}
