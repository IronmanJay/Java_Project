package AspectJ.Xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

public class LoggingAspent {

    public void beforeMethod(JoinPoint joinPoint) {
        // 获取方法的参数
        Object[] args = joinPoint.getArgs();
        // 获取方法的名字
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LoggingAspect==> The method " + methodName + " begin with " + Arrays.asList(args));
    }

    public void afterMethod(JoinPoint joinPoint) {
        // 获取方法的名字
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LoggingAspect==> The method " + methodName + " ends .");
    }

    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        // 获取方法的名字
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LoggingAspect==> The method" + methodName + " end with " + result);
    }

    public void afterThrowingMethod(JoinPoint joinPoint, ArithmeticException ex) {
        // 获取方法的名字
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LoggingAspect==> The method" + methodName + " occurs Exception: " + ex);
    }

    public Object aroundMethod(ProceedingJoinPoint pjp) {
        // 执行目标方法
        try {
            // 前置通知
            Object result = pjp.proceed();
            // 返回通知
            return result;
        } catch (Throwable throwable) {
            // 异常通知
            throwable.printStackTrace();
        } finally {
            // 后置通知
        }
        return null;
    }

}
