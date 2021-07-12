package Ajou.ucandoit.dto;

import Ajou.ucandoit.domain.User;
import lombok.*;

import java.util.Map;

@Getter @Setter
@NoArgsConstructor
@Builder
@ToString
public class UserSaveRequestDto {
    private String userName;
    private String pwd;
    private String email;
    private String nickname;

    public UserSaveRequestDto(String userName, String pwd, String email, String nickname) {
        this.userName = userName;
        this.pwd = pwd;
        this.email = email;
        this.nickname = nickname;
    }

    public User toEntity(String hashPwd) {
        return User.builder()
                .userName(userName)
                .email(email)
                .pwd(hashPwd)
                .nickName(nickname)
                .build();
    }
}
