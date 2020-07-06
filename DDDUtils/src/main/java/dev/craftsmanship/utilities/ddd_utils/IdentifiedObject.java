package dev.craftsmanship.utilities.ddd_utils;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

import static dev.craftsmanship.utils.errors.Errors.*;

@MappedSuperclass
@Getter
public abstract class IdentifiedObject<T extends Identity> extends DomainObject implements Serializable {

    private T identity;

    protected IdentifiedObject(@NotNull Class identityClass){
        try {
            this.identity = (T) Identity.newInstance(identityClass);
        } catch(Throwable t){
            undefinedError(t.getMessage(), t);
        }
    }

    protected IdentifiedObject() { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdentifiedObject)) return false;
        IdentifiedObject<?> entity = (IdentifiedObject<?>) o;
        return identity.equals(entity.identity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity);
    }
}
