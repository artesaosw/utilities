package dev.craftsmanship.utilities.ddd_utils;

import dev.craftsmanship.utils.errors.UnexpectedErrorException;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.UUID;

@Getter
public abstract class Identity implements Serializable {

    private UUID value;

    protected Identity() {
        this.value = UUID.randomUUID();
    }

    public static <T extends Identity> T newInstance(@NotNull Class<? extends Identity> identityClass){
        try {
            return (T) identityClass
                    .getDeclaredConstructor()
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                NoSuchMethodException e) {
            throw new UnexpectedErrorException(e.getMessage(),e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identity)) return false;
        Identity identity = (Identity) o;
        return value.equals(identity.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Identity{" +
                "value=" + value +
                '}';
    }
}
