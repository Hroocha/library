package com.hibernate.library.exception;

public class BookException extends Throwable{
    private final String exceptionText;

    public BookException(String exceptionText) {
        this.exceptionText = exceptionText;
    }

    public String printExceptionText(){
        return exceptionText;
    }
}
