package com.kata.tech.exception;

public class BusinessExcpetion extends Exception {

    private ErrorDTO errorDTO;

    public BusinessExcpetion(String message, Throwable cause, ErrorDTO errorDTO) {
        super(message, cause);
        this.errorDTO = errorDTO;
    }

    public BusinessExcpetion(ErrorDTO errorDTO) {
        this.errorDTO = errorDTO;
    }

    public ErrorDTO getErrorDTO() {
        return errorDTO;
    }

    public void setErrorDTO(ErrorDTO errorDTO) {
        this.errorDTO = errorDTO;
    }
}
