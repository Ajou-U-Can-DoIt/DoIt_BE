package Ajou.ucandoit.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 언제 해당 어노테이션이 적용될꺼나
@Target(ElementType.METHOD)
public @interface TokenRequired {
}
