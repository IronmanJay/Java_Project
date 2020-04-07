package AspectJ.Xml;

import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

public class ValidationAspect {

    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object [] args = joinPoint.getArgs();
        System.out.println("ValidationAspect ==> The method " + methodName + " begin with " + Arrays.asList(args));
    }
}
