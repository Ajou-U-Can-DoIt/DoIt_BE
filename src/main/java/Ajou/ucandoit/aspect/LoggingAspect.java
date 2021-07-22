package Ajou.ucandoit.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
//
//    @Before(("execution(* Ajou.ucandoit.service.*.get*(..))")) // 서비스 패키지에 있는 어떤 클래스든 get으로 시작하고 파라미터는 상관없다!
//    public void loggerBefore() {
//        System.out.println("get으로 시작되는 메서드 시작");
//    }
//
//    @After(("execution(* Ajou.ucandoit.service.*.get*(..))")) // 서비스 패키지에 있는 어떤 클래스든 get으로 시작하고 파라미터는 상관없다!
//    public void loggerAfter() {
//        System.out.println("get으로 시작되는 메서드 끝");
//    }

}
