package com.kata.tech.exception;

public enum ErrorCategory {

    NOT_FOUND((byte) 10, "entity.not.found"),
    REQUEST_NOT_ALLOWED((byte) 20, "request.not.allowed"),
    INVALID_DATA((byte) 30, "invalid.data"),
    MISSING_MANDATORY_FIELD((byte) 40, "missing.mandatory.field");

    private byte  errorNumber;
    private String errorDescription;

    private ErrorCategory(byte errorNumber, String errorDescription) {
        this.errorNumber = errorNumber;
        this.errorDescription = errorDescription;
    }

    public byte getErrorNumber() {
        return errorNumber;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
