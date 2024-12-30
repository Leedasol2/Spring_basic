package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * AOP 적용
 * 핵심 로직에서 벗어나 메서드 호출 시간 계산이라는 관점에서 작성
 */
@Aspect
@Component
public class TimeTraceAop {

    // @Around : 메서드 호출 전후 모두 실행. 괄호 안은 포인트 컷 문장이다.
    @Around("execution(* hello.hello_spring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            System.out.println("joinPoint.proceed()");
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
