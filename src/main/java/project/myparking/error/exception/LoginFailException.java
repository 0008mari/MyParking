package project.myparking.error.exception;

import org.springframework.http.HttpStatus;

public class LoginFailException extends CustomException{

    public LoginFailException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public LoginFailException() {
        super(HttpStatus.NOT_FOUND, "로그인 실패. 계정이 존재하지 않으니 회원가입 후에 이용해주세요.");
    }
}
