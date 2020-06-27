package dev.craftsmanship.utils.values;



import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public abstract class ValueBuilder {

    private boolean nullable;

    private Range bounds;

    private String invalidValueMessage;

    public ValueBuilder nullable(boolean nullable){
        this.nullable = nullable;
        return this;
    }

    public ValueBuilder withBounds(@NotNull Range bounds){
        this.bounds = bounds;
        return this;
    }

    public ValueBuilder withInvalidValueMessage(@NotBlank String invalidValueMessage){
        this.invalidValueMessage = invalidValueMessage;
        return this;
    }

}
