package de.anybytes.springbootschulung.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class AspectTest {

    @Around("@annotation(de.anybytes.springbootschulung.aspect.TestAnnotation)")
    public void test(ProceedingJoinPoint point){
        log.info("This Class was executed: "+ point.getSignature().getDeclaringTypeName() +".This Method was executed: "+ point.getSignature().getName());
    }
}
