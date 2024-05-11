package io.barth.library_management_system.exception;

public class PatronHaveBorrowedTheBookException extends RuntimeException{

    String message;

    public PatronHaveBorrowedTheBookException(String message){this.message = message;}
}
