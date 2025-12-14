package com.piggymade.common;

// Functional interface to handle checked exceptions
@FunctionalInterface
public interface CheckedFunction<T, R> {
    R apply(T t) throws Exception;
}
