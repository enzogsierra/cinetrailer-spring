package com.example.cinetrailer.exceptions;

public class StorageException extends RuntimeException
{
    public StorageException(String msg) {
        super(msg);
    }

    public StorageException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
