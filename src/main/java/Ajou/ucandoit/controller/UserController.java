package Ajou.ucandoit.controller;

import Ajou.ucandoit.dto.UserSaveRequestDto;
import Ajou.ucandoit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public Map<String, Object> signUp(@RequestBody  UserSaveRequestDto userSaveRequestDto) throws Exception {
        Map<String, Object> result = new LinkedHashMap<>();

        // 존재하는 아이디 체크 -> 에러, 같은 비번은 프론트 체크.
        // 같은 아이디 있으면 -1, 없으면 0
        int isVaildUserName = userService.isVaildUserName(userSaveRequestDto.getUserName());
        if(isVaildUserName == -1) throw new Exception("이미 존재하는 아이디입니다.");

        // salt 처리하고, pwd salt 처리된거로 업데이트
         String hashPwd = userService.hashPwd(userSaveRequestDto.getPwd());

        // 저장
        String nickName = userService.signUp(userSaveRequestDto.toEntity(hashPwd));

        result.put("msg", "회원가입을 완료했습니다.");
        result.put("nickname", nickName);

        return result;
    }

}
