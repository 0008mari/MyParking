package project.myparking.error.exception;

import org.springframework.http.HttpStatus;

public class NoDataException extends CustomException{

    public NoDataException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public NoDataException() {
        super(HttpStatus.NOT_FOUND, "조회되는 데이터가 없습니다.");
    }
}
