package com.example.pony_project.exceptions;

public class PonyNotFoundException extends RuntimeException{
    public PonyNotFoundException(String message) {
        super(message);
    }
}
