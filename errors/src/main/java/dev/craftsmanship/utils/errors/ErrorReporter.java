package dev.craftsmanship.utils.errors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public interface ErrorReporter {

    void invalidParameter(@NotBlank String message);

    <T extends Throwable> void invalidParameter(@NotBlank String message, @NotNull T exceptionCause);
    
    <S extends Serializable> void invalidParameter(@NotBlank String message, @NotNull S additionalData);

    <T extends Throwable, S extends Serializable> void invalidParameter(@NotBlank String message, @NotNull T exceptionCause, @NotNull S additionalData);

    void invalidOperation(@NotBlank String message);

    <T extends Throwable> void invalidOperation(@NotBlank String message, @NotNull T exceptionCause);

    <S extends Serializable> void invalidOperation(@NotBlank String message, @NotNull S additionalData);

    <T extends Throwable, S extends Serializable> void invalidOperation(@NotBlank String message, @NotNull T exceptionCause, @NotNull S additionalData);

    void inconsistentInformation(@NotBlank String message);

    <T extends Throwable> void inconsistentInformation(@NotBlank String message, @NotNull T exceptionCause);

    <S extends Serializable> void inconsistentInformation(@NotBlank String message, @NotNull S additionalData);

    <T extends Throwable, S extends Serializable> void inconsistentInformation(@NotBlank String message, @NotNull T exceptionCause, @NotNull S additionalData);

    void inconsistentState(@NotBlank String message);

    <T extends Throwable> void inconsistentState(@NotBlank String message, @NotNull T exceptionCause);

    <S extends Serializable> void inconsistentState(@NotBlank String message, @NotNull S additionalData);

    <T extends Throwable, S extends Serializable> void inconsistentState(@NotBlank String message, @NotNull T exceptionCause, @NotNull S additionalData);

    void undefinedError(@NotBlank String message);

    <T extends Throwable> void undefinedError(@NotBlank String message, @NotNull T exceptionCause);

    <S extends Serializable> void undefinedError(@NotBlank String message, @NotNull S additionalData);

    <T extends Throwable, S extends Serializable> void undefinedError(@NotBlank String message, @NotNull T exceptionCause, @NotNull S additionalData);

}
