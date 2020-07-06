package dev.craftsmanship.utils.values;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

public class Values {

    private static final Collection<Class> VALUE_CLASSES = Set.of(Text.class);

    public static boolean valuesClass(@NotNull Class clazz){
        return VALUE_CLASSES.contains(clazz);
    }

}
