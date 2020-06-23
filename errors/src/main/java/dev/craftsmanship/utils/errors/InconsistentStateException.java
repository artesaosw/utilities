package dev.craftsmanship.utils.errors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class InconsistentStateException extends CraftsmanshipException{
    public InconsistentStateException(@NotBlank String message, @NotNull Serializable additionalData) {
        super(message, additionalData);
    }

    public InconsistentStateException(@NotBlank String message, @NotNull Throwable cause, @NotNull Serializable additionalData) {
        super(message, cause, additionalData);
    }

    public InconsistentStateException(String message) {
        super(message);
    }

    public InconsistentStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
