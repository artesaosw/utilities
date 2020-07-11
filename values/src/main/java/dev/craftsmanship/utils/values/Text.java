package dev.craftsmanship.utils.values;

import dev.craftsmanship.utils.validations.GenericValidator;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Text<V extends GenericValidator> extends Value<String,V> {

    Text(){}

    Text(String invalidValueMessage) {
        setInvalidMessage(invalidValueMessage);
    }


    @Override
    protected Class valueClass() {
        return String.class;
    }
}

