package io.barth.library_management_system.exception;

public class EntityNotFoundException extends  RuntimeException{

    public EntityNotFoundException(String message){
        super(message);
    }
}
