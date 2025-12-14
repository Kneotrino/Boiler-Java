package com.piggymade.common;

// Functional interface for checked exceptions with BiFunction
@FunctionalInterface
public interface CheckedBiFunction<T, U, R> {
    R apply(T t, U u) throws Exception;
}