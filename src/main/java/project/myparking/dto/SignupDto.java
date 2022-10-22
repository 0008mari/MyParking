package project.myparking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.myparking.domain.User;

@Getter
@AllArgsConstructor
public class SignupDto {

    private String email;
    private String username;
    private String password;
//    private String passwordConfirmation;
    public User toEntity(String email, String username, String password) {
        return new User(email, username, password);
    }

}
