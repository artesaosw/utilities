package dev.craftsmanship.utils.errors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class ExceptionReporter implements ErrorReporter {

    ExceptionReporter(){ }

    private Map<ErrorType, Class> exceptionMapping =
            Map.of(
                    ErrorType.INVALID_ARGUMENT, IllegalArgumentException.class,
                    ErrorType.INVALID_OPERATION, InvalidOperationException.class,
                    ErrorType.INCONSISTENT_STATE, InconsistentStateException.class,
                    ErrorType.INCONSISTENT_INFORMATION, InconsistentInformationException.class,
                    ErrorType.UNDEFINED_ERROR, UndefinedErrorException.class);

    private  <T extends RuntimeException> Class<T> exceptionClass(ErrorType errorType){
        return (exceptionMapping.containsKey(errorType) ?
                exceptionMapping.get(errorType) :
                exceptionMapping.get(ErrorType.UNDEFINED_ERROR));
    }

    private <T extends RuntimeException> void throwException(Class<T> exceptionClass, String message) {
        try {

            throw ((T)exceptionClass
                    .getConstructor(String.class)
                    .newInstance(message));

        } catch (NoSuchMethodException |
                InstantiationException |
                IllegalAccessException |
                InvocationTargetException e) {

            throw new RuntimeException("Unexpected error", e);

        }
    }

    private <T extends Throwable, R extends RuntimeException> void throwException(
            Class<R> exceptionClass, String message, T cause) {

        try {

            throw exceptionClass

                    .getConstructor(
                            String.class,
                            Throwable.class)

                    .newInstance(
                            message,
                            cause);

        } catch (NoSuchMethodException |
                InstantiationException |
                IllegalAccessException |
                InvocationTargetException e) {

            throw new RuntimeException("Unexpected error", e);

        }
    }

    private <T extends Throwable, R extends RuntimeException, S extends Serializable> void throwException(
            Class<R> exceptionClass, String message, S additionalData) {

        try {

            throw exceptionClass

                    .getConstructor(
                            String.class,
                            Serializable.class)

                    .newInstance(
                            message,
                            additionalData);

        } catch (NoSuchMethodException |
                InstantiationException |
                IllegalAccessException |
                InvocationTargetException e) {

            throw new RuntimeException("Unexpected error", e);

        }
    }

    private <T extends Throwable, R extends RuntimeException, S extends Serializable> void throwException(
            Class<R> exceptionClass, String message, T cause, S additionalData) {

        try {

            throw exceptionClass

                    .getConstructor(
                            String.class,
                            Throwable.class,
                            Serializable.class)

                    .newInstance(
                            message,
                            cause,
                            additionalData);

        } catch (NoSuchMethodException |
                InstantiationException |
                IllegalAccessException |
                InvocationTargetException e) {

            throw new RuntimeException("Unexpected error", e);

        }
    }

    @Override
    public void invalidParameter(@NotBlank String message) {
        throwException(
                exceptionClass(ErrorType.INVALID_ARGUMENT),
                message);
    }

    @Override
    public <T extends Throwable> void invalidParameter(@NotBlank String message, @NotNull T exceptionCause) {
        throwException(
                exceptionClass(ErrorType.INVALID_ARGUMENT),
                message,
                exceptionCause);
    }

    @Override
    public <S extends Serializable> void invalidParameter(@NotBlank String message, @NotNull S additionalData) {
        throwException(
                exceptionClass(ErrorType.INVALID_ARGUMENT),
                message,
                additionalData);
    }

    @Override
    public <T extends Throwable, S extends Serializable> void invalidParameter(@NotBlank String message, @NotNull T exceptionCause, @NotNull S additionalData) {
        throwException(
                exceptionClass(ErrorType.INVALID_ARGUMENT),
                message,
                exceptionCause,
                additionalData);
    }

    @Override
    public void invalidOperation(@NotBlank String message) {
        throwException(
                exceptionClass(ErrorType.INVALID_OPERATION),
                message);
    }

    @Override
    public <T extends Throwable> void invalidOperation(@NotBlank String message, @NotNull T exceptionCause) {
        throwException(
                exceptionClass(ErrorType.INVALID_OPERATION),
                message,
                exceptionCause);
    }

    @Override
    public <S extends Serializable> void invalidOperation(@NotBlank String message, @NotNull S additionalData) {
        throwException(
                exceptionClass(ErrorType.INVALID_OPERATION),
                message,
                additionalData);
    }

    @Override
    public <T extends Throwable, S extends Serializable> void invalidOperation(@NotBlank String message, @NotNull T exceptionCause, @NotNull S additionalData) {
        throwException(
                exceptionClass(ErrorType.INVALID_OPERATION),
                message,
                exceptionCause,
                additionalData);
    }

    @Override
    public void inconsistentInformation(@NotBlank String message) {
        throwException(
                exceptionClass(ErrorType.INCONSISTENT_INFORMATION),
                message);
    }

    @Override
    public <T extends Throwable> void inconsistentInformation(@NotBlank String message, @NotNull T exceptionCause) {
        throwException(
                exceptionClass(ErrorType.INCONSISTENT_INFORMATION),
                message,
                exceptionCause);
    }

    @Override
    public <S extends Serializable> void inconsistentInformation(@NotBlank String message, @NotNull S additionalData) {
        throwException(
                exceptionClass(ErrorType.INCONSISTENT_INFORMATION),
                message,
                additionalData);
    }

    @Override
    public <T extends Throwable, S extends Serializable> void inconsistentInformation(@NotBlank String message, @NotNull T exceptionCause, @NotNull S additionalData) {
        throwException(
                exceptionClass(ErrorType.INCONSISTENT_INFORMATION),
                message,
                exceptionCause,
                additionalData);
    }

    @Override
    public void inconsistentState(@NotBlank String message) {
        throwException(
                exceptionClass(ErrorType.INCONSISTENT_STATE),
                message);
    }

    @Override
    public <T extends Throwable> void inconsistentState(@NotBlank String message, @NotNull T exceptionCause) {
        throwException(
                exceptionClass(ErrorType.INCONSISTENT_STATE),
                message,
                exceptionCause);
    }

    @Override
    public <S extends Serializable> void inconsistentState(@NotBlank String message, @NotNull S additionalData) {
        throwException(
                exceptionClass(ErrorType.INCONSISTENT_STATE),
                message,
                additionalData);
    }

    @Override
    public <T extends Throwable, S extends Serializable> void inconsistentState(@NotBlank String message, @NotNull T exceptionCause, @NotNull S additionalData) {
        throwException(
                exceptionClass(ErrorType.INCONSISTENT_STATE),
                message,
                exceptionCause,
                additionalData);
    }

    @Override
    public void undefinedError(@NotBlank String message) {
        throwException(
                exceptionClass(ErrorType.UNDEFINED_ERROR),
                message);
    }

    @Override
    public <T extends Throwable> void undefinedError(@NotBlank String message, @NotNull T exceptionCause) {
        throwException(
                exceptionClass(ErrorType.UNDEFINED_ERROR),
                message,
                exceptionCause);
    }

    @Override
    public <S extends Serializable> void undefinedError(@NotBlank String message, @NotNull S additionalData) {
        throwException(
                exceptionClass(ErrorType.UNDEFINED_ERROR),
                message,
                additionalData);
    }

    @Override
    public <T extends Throwable, S extends Serializable> void undefinedError(@NotBlank String message, @NotNull T exceptionCause, @NotNull S additionalData) {
        throwException(
                exceptionClass(ErrorType.UNDEFINED_ERROR),
                message,
                exceptionCause,
                additionalData);
    }
}
