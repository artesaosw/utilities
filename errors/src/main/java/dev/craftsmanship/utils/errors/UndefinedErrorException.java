package dev.craftsmanship.utils.errors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UndefinedErrorException extends CraftsmanshipException {
    public UndefinedErrorException(@NotBlank String message, @NotNull Serializable additionalData) {
        super(message, additionalData);
    }

    public UndefinedErrorException(@NotBlank String message, @NotNull Throwable cause, @NotNull Serializable additionalData) {
        super(message, cause, additionalData);
    }

    public UndefinedErrorException(String message) {
        super(message);
    }

    public UndefinedErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
