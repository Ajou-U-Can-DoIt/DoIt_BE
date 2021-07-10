package Ajou.ucandoit.controller;

import Ajou.ucandoit.aspect.TokenRequired;
import Ajou.ucandoit.domain.User;
import Ajou.ucandoit.dto.UserLoginRequestDto;
import Ajou.ucandoit.dto.UserSaveRequestDto;
import Ajou.ucandoit.security.SecurityService;
import Ajou.ucandoit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;

    @Autowired
    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @PostMapping("/signup")
    public Map<String, Object> signUp(@RequestBody UserSaveRequestDto userSaveRequestDto){
        Map<String, Object> result = new LinkedHashMap<>();

        // 존재하는 아이디 체크 -> 에러, 같은 비번은 프론트 체크.
        // 같은 아이디 있으면 -1, 없으면 0
        int isVaildUserName = userService.isValidUserName(userSaveRequestDto.getUserName());
        if(isVaildUserName == -1) throw new IllegalArgumentException("이미 존재하는 아이디입니다.");

        // salt 처리하고, pwd salt 처리된거로 업데이트
         String hashPwd = userService.hashPwd(userSaveRequestDto.getPwd());

        // 저장
        String nickName = userService.signUp(userSaveRequestDto.toEntity(hashPwd));

        result.put("msg", "회원가입을 완료했습니다.");
        result.put("nickname", nickName);

        return result;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserLoginRequestDto userLoginRequestDto){
        Map<String, Object> result = new LinkedHashMap<>();

        //id로 user 정보 갖고오고, 없으면 에러
        User user = userService.getUserByUserName(userLoginRequestDto.getUserName());
        if(user==null) throw new IllegalArgumentException("존재하지 않는 아이디입니다.");

        //pwd 체크 일치 하지 않으면 에러
        boolean pwdCheck = userService.verifyPwd(userLoginRequestDto.getPwd(), user.getPwd());
        if(!pwdCheck) throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        // 다 통과되면 토큰 발급
        String token = securityService.createToken(userLoginRequestDto.getUserName());

        result.put("msg", "로그인에 성공했습니다.");
        result.put("token", token);

        return result;
    }

    @GetMapping("/tokenTest")
    @TokenRequired
    public String tokenTest() {
        return "hello";
    }

}
