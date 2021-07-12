package Ajou.ucandoit.controller;

import Ajou.ucandoit.dto.UserSaveRequestDto;
import Ajou.ucandoit.repository.UserRepository;
import Ajou.ucandoit.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserControllerTest {

    private final UserService userService;
    private UserRepository userRepository;

    UserSaveRequestDto userSaveRequestDto;

    @Autowired
    public UserControllerTest(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @BeforeEach
    void before() {
        userSaveRequestDto = UserSaveRequestDto.builder()
                .userName("testId")
                .pwd("testPwd")
                .nickname("testNickname")
                .build();
    }

    @Test
    void signUp() {
        int vaildUserName = userService.isValidUserName(userSaveRequestDto.getUserName());
        Assertions.assertThat(vaildUserName).isEqualTo(0);

        String hashPwd = userService.hashPwd(userSaveRequestDto.getPwd());
        userService.signUp(userSaveRequestDto.toEntity(hashPwd));

        int againValidCheck = userService.isValidUserName(userSaveRequestDto.getUserName());
        Assertions.assertThat(againValidCheck).isEqualTo(-1);
    }

}