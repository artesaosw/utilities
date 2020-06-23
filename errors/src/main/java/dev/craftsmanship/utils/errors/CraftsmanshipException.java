package dev.craftsmanship.utils.errors;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
public class CraftsmanshipException extends RuntimeException {

    private Serializable additionalData;

    public CraftsmanshipException(
            @NotBlank String message, @NotNull Serializable additionalData){
        super(message);
        this.additionalData = additionalData;
    }

    public CraftsmanshipException(
            @NotBlank String message, @NotNull Throwable cause, @NotNull Serializable additionalData){
        super(message, cause);
        this.additionalData = additionalData;
    }

    public CraftsmanshipException(String message) {
        super(message);
    }

    public CraftsmanshipException(String message, Throwable cause) {
        super(message, cause);
    }
}
