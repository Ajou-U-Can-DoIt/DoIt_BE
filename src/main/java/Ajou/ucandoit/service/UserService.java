package Ajou.ucandoit.service;

import Ajou.ucandoit.dto.UserSaveRequestDto;

public interface UserService {
    String signUp(UserSaveRequestDto userSaveRequestDto);

    int isVaildUserName(String userName);
}
