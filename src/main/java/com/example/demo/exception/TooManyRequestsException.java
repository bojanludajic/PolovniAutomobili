package com.example.demo.exception;

public class TooManyRequestsException extends RuntimeException {

    public TooManyRequestsException() {
        super("Previse puta ste upotrebili ovu funkcionalnost. Molim sacekajte ili se vratite na pocetnu stranu (ukoliko je moguce)!");
    }
}
