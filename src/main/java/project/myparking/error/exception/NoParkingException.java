package project.myparking.error.exception;

import org.springframework.http.HttpStatus;

public class NoParkingException extends CustomException{

    public NoParkingException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public NoParkingException() {
        super(HttpStatus.NOT_FOUND, "조회되는 주차장이 없습니다.");
    }
}
