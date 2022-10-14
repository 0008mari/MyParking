package project.myparking.global.exception;

import org.springframework.http.HttpStatus;

public class NoDataException extends CustomException{

    public NoDataException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public NoDataException() {
        super(HttpStatus.BAD_REQUEST, "데이터가 없습니다.");
    }
}
