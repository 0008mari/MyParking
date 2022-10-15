package project.myparking.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.myparking.error.exception.CustomException;
import project.myparking.global.api.CustomResponse;

@RestControllerAdvice
class CustomExceptionHandler extends Exception {

    private final boolean SUCCESS = false;

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<CustomResponse> handleGlobalBusinessException(CustomException e) {
        String message = e.getMessage();
        HttpStatus httpStatus = e.getHttpStatus();
        return CustomResponse.CommonResponse(httpStatus, SUCCESS, message);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CustomResponse> handleException(Exception e) {

        String message = e.getMessage();

        return CustomResponse.CommonResponse(HttpStatus.INTERNAL_SERVER_ERROR, SUCCESS, message);
    }
}
