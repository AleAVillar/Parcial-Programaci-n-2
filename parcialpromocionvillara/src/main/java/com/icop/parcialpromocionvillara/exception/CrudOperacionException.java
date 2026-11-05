package com.icop.parcialpromocionvillara.exception;

public class CrudOperacionException extends RuntimeException {
    public CrudOperacionException(String mensaje) {
        super(mensaje);
    }
}
