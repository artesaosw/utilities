package dev.craftsmanship.utilities.ddd_utils;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Getter
public abstract class DomainEvent extends IdentifiedObject{

    DomainEventID eventID;

    LocalDateTime instant;

    String message;

    DomainObject additionalData;

    Throwable exception;

    int orderNumber;

    private void initialize(int orderNumber){
        this.instant = LocalDateTime.now();
        this.orderNumber = orderNumber;
    }

    protected DomainEvent(boolean success, int orderNumber) {
        super(DomainEventID.class);
        initialize(orderNumber);
    }

    protected <T extends DomainObject> DomainEvent(@NotNull T additionalData){
        this(true, 0);
        this.additionalData = additionalData;
    }

    protected <T extends DomainObject> DomainEvent(@NotNull T additionalData, @PositiveOrZero int order){
        this(true, order);
        this.additionalData = additionalData;
    }

    protected <T extends Throwable> DomainEvent(@NotNull T exception){
        this(false, 0);
        this.exception = exception;
    }

    protected <T extends Throwable> DomainEvent(@NotNull T exception, @PositiveOrZero int order){
        this(false, order);
        this.exception = exception;
    }

    public void incrementOrder(){
        orderNumber++;
    }
}
