package com.project.mpm.exceptions;

public class NoSuchEmployeeExistsException extends RuntimeException {
    public NoSuchEmployeeExistsException(String msg) {
        super(msg);
    }

}