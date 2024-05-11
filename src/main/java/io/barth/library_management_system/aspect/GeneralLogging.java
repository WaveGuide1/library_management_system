package io.barth.library_management_system.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class GeneralLogging {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* io.barth.library_management_system.book.*.*(..)) || " +
            "execution(* io.barth.library_management_system.patron.*.*(..)) || " +
            "execution(* io.barth.library_management_system.authentication.*.*(..)) || " +
            "execution(* io.barth.library_management_system.borrowingBook.*.*(..))")
    public void loggingPointcut(){}

    @Before("loggingPointcut()")
    public void beforeExecution(JoinPoint joinpoint){
        logger.info("Entering method: {} with arguments: {} ,Class Name:{}",
                joinpoint.getSignature().getName(), joinpoint.getArgs(),
                joinpoint.getSignature().getDeclaringTypeName());

    }

    @After(value = "loggingPointcut()")
    public void afterExecutionJoinPoint(JoinPoint joinpoint){
        logger.info("Exiting method: {} , Class Name:{}",
                joinpoint.getSignature().getName(),
                joinpoint.getSignature().getDeclaringTypeName());

    }

    @AfterThrowing(pointcut = "loggingPointcut()", throwing = "e")
    public void afterException(JoinPoint joinpoint, Exception e){
        logger.info("After method {} :: ,was invoked from class {} :: ", e.getMessage(), e.getClass());
    }
}
