package dev.craftsmanship.utils.errors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class InconsistentInformationException extends CraftsmanshipException {
    public <S extends Serializable> InconsistentInformationException(@NotBlank String message, @NotNull S additionalData) {
        super(message, additionalData);
    }

    public <S extends Serializable> InconsistentInformationException(@NotBlank String message, @NotNull Throwable cause, @NotNull S additionalData) {
        super(message, cause, additionalData);
    }

    public InconsistentInformationException(String message) {
        super(message);
    }

    public InconsistentInformationException(String message, Throwable cause) {
        super(message, cause);
    }
}
