package com.SocieadeJava.MicroServiceB.exceptions;

public class ResourceInUseException extends RuntimeException{
    public ResourceInUseException(String message){
        super(message);
    }
}
