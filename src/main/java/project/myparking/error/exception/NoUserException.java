package project.myparking.error.exception;

import org.springframework.http.HttpStatus;

public class NoUserException extends CustomException{

    public NoUserException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public NoUserException() {
        super(HttpStatus.NOT_FOUND, "조회되는 사용자가 없습니다.");
    }
}
