package Ajou.ucandoit.security;

import Ajou.ucandoit.domain.Auth;
import Ajou.ucandoit.util.ResFormat;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/security")
public class SecurityController {

    private final SecurityService securityService;

    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/refresh")
    public ResFormat refresh(@CookieValue(value = "refresh") Cookie cookie, HttpServletResponse response) {
        Map<String, Object> result = new LinkedHashMap<>();

        String token = cookie.getValue();
        Auth refreshToken = securityService.getRefreshToken(token);

        if(refreshToken == null) throw new IllegalArgumentException("잘못된 Refresh Token 입니다.");

        if(securityService.checkValidationRefresh(refreshToken)){
            String accessToken = securityService.createToken(refreshToken.getSubject());

            securityService.deleteRefreshToken(refreshToken.getSubject());

            Auth newRefreshToken = securityService.createRefreshToken(accessToken, refreshToken.getSubject());
            securityService.saveRefreshToken(newRefreshToken);

            result.put("token", accessToken);

            // 리프래시 토큰 쿠키 주입 !
            Cookie refreshCookie = new Cookie("refresh", newRefreshToken.getRefreshToken());
            refreshCookie.setMaxAge(60*60*24*14);
            refreshCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
            refreshCookie.setHttpOnly(true);
            response.addCookie(refreshCookie);

            return new ResFormat(true, 201L, result);


        } else{
            //delete
            securityService.deleteRefreshToken(refreshToken.getSubject());
            throw new IllegalArgumentException("Refresh Token 유효기간이 지났습니다.");
        }
    }

}
