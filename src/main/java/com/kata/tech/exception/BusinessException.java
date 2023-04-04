package com.kata.tech.exception;

public class BusinessException extends Exception {

    private ErrorDTO errorDTO;

    public BusinessException(String message, Throwable cause, ErrorDTO errorDTO) {
        super(message, cause);
        this.errorDTO = errorDTO;
    }

    public BusinessException(ErrorDTO errorDTO) {
        this.errorDTO = errorDTO;
    }

    public ErrorDTO getErrorDTO() {
        return errorDTO;
    }

    public void setErrorDTO(ErrorDTO errorDTO) {
        this.errorDTO = errorDTO;
    }
}
