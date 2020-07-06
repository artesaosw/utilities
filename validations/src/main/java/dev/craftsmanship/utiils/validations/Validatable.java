package dev.craftsmanship.utiils.validations;

import dev.craftsmanship.utils.streams.Result;

import java.util.Collection;
import java.util.Map;

public interface Validatable {

    <T extends GenericValidator> Map<String, Collection<T>> validators();

    Map<String, Result> validate();

}
