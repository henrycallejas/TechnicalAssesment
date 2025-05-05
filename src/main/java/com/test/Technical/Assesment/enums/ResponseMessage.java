package com.test.Technical.Assesment.enums;

public enum ResponseMessage {
    OK("Request completed correctly"),
    CREATED("Resource created successfully"),
    NOT_FOUND("Resource not found"),
    ERROR("An unexpected error occurred"),
    EMPTY("There are no records to display");

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
