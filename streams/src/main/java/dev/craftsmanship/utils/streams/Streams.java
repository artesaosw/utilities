package dev.craftsmanship.utils.streams;

import dev.craftsmanship.utils.errors.Errors;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {

    public static Result proceed(@NotNull Operation operation) {
        try {
            return operation.proceed();
        } catch (Throwable t) {
            return Result.negative(t.getMessage(), t);
        }
    }

    public static void proceed(@NotNull Block block) {
        try {
            block.run();
        } catch (Throwable t) {
            Errors.instance().undefinedError(t.getMessage(),t);
        }
    }

    public static void proceed(@NotNull Block block, @NotNull Block onError) {
        try {
            block.run();
        } catch (Throwable t) {
            Errors.instance().undefinedError(t.getMessage(),t);
        }
    }

    public static void proceed(@NotNull Block block, @NotNull Block onError, @NotNull Block runAnyway) {
        try {
            block.run();
        } catch (Throwable t) {
            onError.run();
        } finally {
            runAnyway.run();
        }
    }

    private static <T extends Object> Stream<T> filter(Stream<T> stream, Predicate<T> predicate){
        return stream.filter(predicate);
    }

    private static <T,R extends Object> Stream<R> map(Stream<T> stream, Function<T,R> mapper){
        return stream.map(mapper);
    }

    private static <R extends Object> R reduce(Stream<R> stream, R identity, BinaryOperator<R> accumulator){
        return stream.reduce(identity,accumulator);
    }

    private static <T extends Object> List<T> collect(Stream<T> stream){
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    public static <T,R extends Object> R proceed(Collection<T> collection, Predicate<T> predicate, Function<T, R> mapper,
                                                   R identity, BinaryOperator<R> accumulator){
        return (R) reduce(map(filter(collection.stream(), predicate), mapper), identity, accumulator);
    }

    public static <T,R extends Object> R processar(Collection<T> collection, Function<T, R> mapper, R identity,
                                                   BinaryOperator<R> accumulator){
        return (R) reduce(map(collection.stream(), mapper),identity, accumulator);
    }

    public static <T extends Object> List<T> filter(Collection<T> collection, Predicate<T> predicate){
        return collect(filter(collection.stream(),predicate));
    }

    public static <T extends Object> void forEach(Collection<T> colecao, Consumer<T> acao){
        colecao.forEach(acao);
    }

    public static <T,R extends Object> void forEach(Map<T,R> mapa, BiConsumer<T,R> acao){
        mapa.forEach(acao);
    }

    public static void decide(Condition condition, Block conditionValidBlock){
        if (condition.eval()){
            conditionValidBlock.run();
        }
    }

    public static <T extends Object> T decide(Condition condition, Expression conditionValidBlock, T defaultValue){
        if (condition.eval()){
            return conditionValidBlock.eval();
        }
        return defaultValue;
    }

    public static <T extends Object> T decide(Condition condition, Expression conditionValidExpression, Expression conditionnotValidExpression){
        if (condition.eval()){
            return conditionValidExpression.eval();
        } else {
            return conditionnotValidExpression.eval();
        }
    }

    public static void decide(Condition condition, Block conditionValidBlock, Block conditionNotValidBlock){
        if (condition.eval()){
            conditionValidBlock.run();
        } else {
            conditionNotValidBlock.run();
        }
    }
}
