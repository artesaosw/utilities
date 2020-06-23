package dev.craftsmanship.utils.errors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UnexpectedErrorException extends CraftsmanshipException {

    public  UnexpectedErrorException(@NotBlank String message, @NotNull Serializable additionalData) {
        super(message, additionalData);
    }

    public  UnexpectedErrorException(@NotBlank String message, @NotNull Throwable cause, @NotNull Serializable additionalData) {
        super(message, cause, additionalData);
    }

    public UnexpectedErrorException(String message) {
        super(message);
    }

    public UnexpectedErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
