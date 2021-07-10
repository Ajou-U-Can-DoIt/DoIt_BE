package Ajou.ucandoit.service;

import Ajou.ucandoit.domain.User;
import Ajou.ucandoit.dto.UserSaveRequestDto;

import java.util.Map;

public interface UserService {
    String signUp(User user);

    int isValidUserName(String userName);

    String hashPwd(String pwd);

    User getUserByUserName(String userName);

    boolean verifyPwd(String pwd, String hashPwd);
}
