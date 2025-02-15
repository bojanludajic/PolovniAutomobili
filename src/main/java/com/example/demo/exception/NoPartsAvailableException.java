package com.example.demo.exception;

public class NoPartsAvailableException extends RuntimeException {

    public NoPartsAvailableException() {
        super("Za ovaj auto trenutno nema delova!");
    }

}
