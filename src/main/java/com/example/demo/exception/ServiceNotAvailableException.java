package com.example.demo.exception;

public class ServiceNotAvailableException extends  RuntimeException {

    public ServiceNotAvailableException(String service) {
        super("Servis za " + service + " trenutno nije dostupan. Pokusaj ponovo kasnije!");
    }

}
