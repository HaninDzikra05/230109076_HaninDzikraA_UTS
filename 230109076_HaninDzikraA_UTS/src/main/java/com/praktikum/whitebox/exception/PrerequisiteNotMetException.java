package com.praktikum.whitebox.exception;

/**
 * Exception yang dilempar ketika prasyarat mata kuliah tidak terpenuhi
 */

public class PrerequisiteNotMetException extends RuntimeException {

    public PrerequisiteNotMetException(String message) {
        super(message);
    }

    public PrerequisiteNotMetException(String message, Throwable cause) {
        super(message, cause);
    }
}