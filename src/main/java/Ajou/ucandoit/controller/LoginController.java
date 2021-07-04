package Ajou.ucandoit.controller;

import Ajou.ucandoit.dto.UserSaveRequestDto;
import Ajou.ucandoit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public String signUp(UserSaveRequestDto userSaveRequestDto) {
        return userService.signUp(userSaveRequestDto);
    }

}
