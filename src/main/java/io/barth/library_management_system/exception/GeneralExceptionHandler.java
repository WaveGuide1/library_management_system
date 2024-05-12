package io.barth.library_management_system.exception;

public class GeneralExceptionHandler extends RuntimeException{

    String message;

    public GeneralExceptionHandler(String message){this.message = message;}
}
