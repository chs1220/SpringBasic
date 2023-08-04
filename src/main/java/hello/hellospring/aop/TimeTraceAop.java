package hello.hellospring.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component   // spring bean에 등록 (보통 aop는 springConfig에 작성)
@Aspect      // aop 임을 알려주는 어노테이션
public class TimeTraceAop {


    @Around("execution(* hello.hellospring..*(..))")    // aop 적용할 범위를 hellospring 패키지 하위로 지정함. 보통 패키지 단위로 지정함
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs +
                    "ms");
        }
    }
}