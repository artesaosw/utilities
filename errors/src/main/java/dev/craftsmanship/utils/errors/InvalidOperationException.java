package dev.craftsmanship.utils.errors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class InvalidOperationException extends CraftsmanshipException {
    public  InvalidOperationException(@NotBlank String message, @NotNull Serializable additionalData) {
        super(message, additionalData);
    }

    public  InvalidOperationException(@NotBlank String message, @NotNull Throwable cause, @NotNull Serializable additionalData) {
        super(message, cause, additionalData);
    }

    public InvalidOperationException(String message) {
        super(message);
    }

    public InvalidOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
