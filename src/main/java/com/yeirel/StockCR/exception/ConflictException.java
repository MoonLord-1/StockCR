package com.yeirel.StockCR.exception;

public class ConflictException extends  RuntimeException{
    public ConflictException(String message){
        super(message);
    }
}
