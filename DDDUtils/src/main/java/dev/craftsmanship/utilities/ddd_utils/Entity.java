package dev.craftsmanship.utilities.ddd_utils;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Getter
public abstract class Entity<T extends Identity> extends IdentifiedObject implements DomainObject{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long databaseID;

    protected Entity(@NotNull Class identityClass) {
        super(identityClass);
    }

}
