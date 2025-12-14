package com.piggymade.common;

@FunctionalInterface
public interface TokenFunctionHandler {
    String apply(String... args) throws Exception;
}