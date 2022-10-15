package project.myparking.error.exception;

import org.springframework.http.HttpStatus;

public class NoReviewException extends CustomException{

    public NoReviewException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public NoReviewException() {
        super(HttpStatus.NOT_FOUND, "조회되는 리뷰가 없습니다.");
    }
}
