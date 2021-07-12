package Ajou.ucandoit.aspect;

import Ajou.ucandoit.security.SecurityService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SecurityAspect {

    private final SecurityService securityService;

    @Autowired
    public SecurityAspect(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Before("@annotation(tokenRequired)")
    public void authenticateWithToken(TokenRequired tokenRequired){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String wholeToken = request.getHeader("Authorization");
        String[] bearer = wholeToken.split("Bearer ");
        String token = bearer[1];

        if (token == null) {
            throw new IllegalArgumentException("토큰이 비었습니다.");
        }

        if(securityService.getClamims(token) == null || securityService.getSubject(token) == null)
            throw new IllegalArgumentException("토큰에 클레임이나 서브젝트가 없습니다.");

        // 서브젝트 기반으로 자체 인증 로직
    }
}
