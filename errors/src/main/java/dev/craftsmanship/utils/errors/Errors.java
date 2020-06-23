package dev.craftsmanship.utils.errors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

public class Errors {

    private static  Class DEFAULT_INSTANCE = ExceptionReporter.class;

    private static ErrorReporter instance;

    public static void setInstance(@NotNull ErrorReporter reporter){
        instance = reporter;
    }

    public static ErrorReporter instance(){
        if (instance == null) {
            try {
                instance = (ErrorReporter) DEFAULT_INSTANCE
                        .getDeclaredConstructor(Null.class)
                        .newInstance();
            } catch (NoSuchMethodException |
                    InstantiationException |
                    IllegalAccessException |
                    InvocationTargetException e) {
                throw new UnexpectedErrorException(e.getMessage(), e);
            }
        }
        return instance;
    }
}
