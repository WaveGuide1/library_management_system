package io.barth.library_management_system.exception;

public class UserAlreadyRegisterException extends  RuntimeException {
    String message;

    public UserAlreadyRegisterException(String message) {
        this.message = message;
    }
}
