package project.myparking.global.api;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class CustomResponse {

    private boolean success;
    private String message;
    private Object data;

    private CustomResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    private CustomResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static ResponseEntity<CustomResponse> CommonResponse(HttpStatus httpStatus, boolean success, String message, Object data) {
        return ResponseEntity.status(httpStatus).body(new CustomResponse(success, message, data));
    }

    public static ResponseEntity<CustomResponse> CommonResponse(HttpStatus httpStatus, boolean success, String message) {
        return ResponseEntity.status(httpStatus).body(new CustomResponse(success, message));
    }
}
