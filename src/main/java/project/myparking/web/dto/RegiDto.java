package project.myparking.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
//@AllArgsConstructor
public class RegiDto {

    private String alias ;
    private String email;
    private String password;
    private String name;

//    @Builder
    public RegiDto(String alias, String email, String password, String name) {
        this.alias = alias;
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
