package com.library.demo.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException() {}

    public BookNotFoundException(String s) {
        super(s);
    }
}
