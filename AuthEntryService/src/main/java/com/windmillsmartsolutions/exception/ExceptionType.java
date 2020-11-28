package com.windmillsmartsolutions.exception;

public enum ExceptionType {
    ENTITY_NOT_FOUND("NOTFOUND"),
    DUPLICATE_ENTITY("DUPLICATE"),
    ENTITY_EXCEPTION("EXCEPTION"),
    DUPLICATE_CUSTID("DUPLICATE"),
    UNAUTHORIZED_ACCESS("UNAUTHORIZED_ACCESS");

    String value;

    ExceptionType(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }
}