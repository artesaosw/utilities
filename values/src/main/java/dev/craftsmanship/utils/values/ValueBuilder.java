package dev.craftsmanship.utils.values;



import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public abstract class ValueBuilder {

    private String invalidValueMessage;

    public ValueBuilder withInvalidValueMessage(@NotBlank String invalidValueMessage){
        this.invalidValueMessage = invalidValueMessage;
        return this;
    }

}
