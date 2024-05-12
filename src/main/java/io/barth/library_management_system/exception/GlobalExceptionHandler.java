package io.barth.library_management_system.exception;

import io.barth.library_management_system.utility.GeneralResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponseEntity<String>> validationException(MethodArgumentNotValidException notValidException){

        GeneralResponseEntity<String> responseEntity = new GeneralResponseEntity<>();
        String message = notValidException.getBindingResult().getFieldError()
                .getDefaultMessage();
        responseEntity.setMessage(message);
        return new ResponseEntity<>(responseEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<GeneralResponseEntity<String>> recordNotFoundException(RecordNotFoundException exception){

        GeneralResponseEntity<String> responseEntity = new GeneralResponseEntity<>();
        responseEntity.setMessage(exception.message);
        return new ResponseEntity<>(responseEntity, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyRegisterException.class)
    public ResponseEntity<GeneralResponseEntity<String>> userAlreadyExist(UserAlreadyRegisterException exception){

        GeneralResponseEntity<String> responseEntity = new GeneralResponseEntity<>();
        responseEntity.setMessage(exception.message);
        return new ResponseEntity<>(responseEntity, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<GeneralResponseEntity<String>> authenticationFailed(AuthenticationFailedException exception){

        GeneralResponseEntity<String> responseEntity = new GeneralResponseEntity<>();
        responseEntity.setMessage(exception.message);
        return new ResponseEntity<>(responseEntity, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(GeneralExceptionHandler.class)
    public ResponseEntity<GeneralResponseEntity<String>> generalException(GeneralExceptionHandler exception){

        GeneralResponseEntity<String> responseEntity = new GeneralResponseEntity<>();
        responseEntity.setMessage(exception.message);
        return new ResponseEntity<>(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PatronHaveBorrowedTheBookException.class)
    public ResponseEntity<GeneralResponseEntity<String>> patronHaveTheBookException(PatronHaveBorrowedTheBookException exception){

        GeneralResponseEntity<String> responseEntity = new GeneralResponseEntity<>();
        responseEntity.setMessage(exception.message);
        return new ResponseEntity<>(responseEntity, HttpStatus.CONFLICT);
    }
}
