package Ajou.ucandoit.controller;

import Ajou.ucandoit.aspect.TokenRequired;
import Ajou.ucandoit.domain.Auth;
import Ajou.ucandoit.domain.User;
import Ajou.ucandoit.dto.UserLoginRequestDto;
import Ajou.ucandoit.dto.UserSaveRequestDto;
import Ajou.ucandoit.security.SecurityService;
import Ajou.ucandoit.service.UserService;
import Ajou.ucandoit.util.ResFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;

    @Autowired
    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @PostMapping("/signup")
    public ResFormat signUp(@RequestBody UserSaveRequestDto userSaveRequestDto){

        // 존재하는 아이디 체크 -> 에러, 같은 비번은 프론트 체크.
        // 같은 아이디 있으면 -1, 없으면 0
        int isVaildUserName = userService.isValidUserName(userSaveRequestDto.getUserName());
        if(isVaildUserName == -1) throw new IllegalArgumentException("이미 존재하는 아이디입니다.");

        // salt 처리하고, pwd salt 처리된거로 업데이트
         String hashPwd = userService.hashPwd(userSaveRequestDto.getPwd());

        // 저장
        String nickName = userService.signUp(userSaveRequestDto.toEntity(hashPwd));

        Map<String , Object> result = new HashMap<>();
        result.put("nickName", nickName);

        return new ResFormat(true,201L, result);
    }

    @PostMapping("/login")
    public ResFormat login(@RequestBody UserLoginRequestDto userLoginRequestDto, HttpServletResponse response){
        String subject = userLoginRequestDto.getUserName();

        //id로 user 정보 갖고오고, 없으면 에러
        User user = userService.getUserByUserName(subject);
        if(user==null) throw new IllegalArgumentException("존재하지 않는 아이디입니다.");

        //pwd 체크 일치 하지 않으면 에러
        boolean pwdCheck = userService.verifyPwd(userLoginRequestDto.getPwd(), user.getPwd());
        if(!pwdCheck) throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        // 다 통과되면 토큰 발급
        String token = securityService.createToken(subject);

        // gen refresh and save repo 2주짜리
        Auth refreshToken = securityService.createRefreshToken(token, subject);
        securityService.deleteRefreshToken(refreshToken.getSubject());
        securityService.saveRefreshToken(refreshToken);

        // 리프래시 토큰 쿠키 주입 !
        Cookie refreshCookie = new Cookie("refresh", refreshToken.getRefreshToken());
        refreshCookie.setMaxAge(60*60*24*14);
        refreshCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
        refreshCookie.setHttpOnly(true);
        response.addCookie(refreshCookie);

        Map<String , Object> result = new HashMap<>();
        result.put("token", token);
        result.put("nickName", user.getNickName());

        return new ResFormat(true,201L,result);

    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        Cookie refreshCookie = new Cookie("refresh", "None");
        refreshCookie.setMaxAge(0);
        response.addCookie(refreshCookie);
    }

}
