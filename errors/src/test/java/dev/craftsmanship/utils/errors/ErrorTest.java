package dev.craftsmanship.utils.errors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ErrorTest {

    private static final String ERROR_MESSAGE = "error message";

    private static final ErrorReporter reporter = new ExceptionReporter();



    @Test
    public void execute(){
        Throwable t = assertThrows(InconsistentInformationException.class,() -> reporter.inconsistentInformation(ERROR_MESSAGE));
        assertEquals(ERROR_MESSAGE,t.getMessage());

        RuntimeException re = new RuntimeException();
        t = assertThrows(InconsistentStateException.class, () -> reporter.inconsistentState(ERROR_MESSAGE,re));
        assertEquals(ERROR_MESSAGE,t.getMessage());
        assertEquals(t.getCause(),re);

        String obj = "dados";
        t = assertThrows(InvalidOperationException.class, () -> reporter.invalidOperation(ERROR_MESSAGE,obj));
        assertEquals(ERROR_MESSAGE,t.getMessage());
        assertEquals(obj,((CraftsmanshipException) t).getAdditionalData());

        t = assertThrows(InvalidOperationException.class, () -> reporter.invalidOperation(ERROR_MESSAGE,re,obj));
        assertEquals(ERROR_MESSAGE,t.getMessage());
        assertEquals(t.getCause(),re);
        assertEquals(obj,((CraftsmanshipException) t).getAdditionalData());
    }

}
