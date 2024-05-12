package io.barth.library_management_system.exception;

public class RecordNotFoundException extends RuntimeException{

    String message;

    public RecordNotFoundException(String message) {
        this.message = message;
    }
}
