package AspectJ.Annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// 日志切面
@Component // 标识为一个组件
@Aspect // 标识为一个切面
@Order(2)
public class LoggingAspent {

    // 声明切入点表达式
    @Pointcut(value = "execution(* AspectJ.Annotation.*.*(..))")
    public void declarePointCut() {

    }

    // 前置通知：在目标方法（连接点）执行之前执行
    @Before(value = "execution(public int AspectJ.Annotation.ArithmeticCalculatorImpl.add(int,int))")
    public void beforeMethod(JoinPoint joinPoint) {
        // 获取方法的参数
        Object[] args = joinPoint.getArgs();
        // 获取方法的名字
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LoggingAspect==> The method " + methodName + " begin with " + Arrays.asList(args));
    }

    // 后置通知：在目标方法执行之后执行，不管目标方法有没有抛出异常，不能获取方法的结果
    //@After(value = "execution(* AspectJ.Annotation.*.*(..))")
    @After(value = "declarePointCut()")
    public void afterMethod(JoinPoint joinPoint) {
        // 获取方法的名字
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LoggingAspect==> The method " + methodName + " ends .");
    }

    // 返回通知：在目标方法正常执行结束后执行，可以获取到方法的返回值
    // 获取方法的返回值：通过returning来指定一个名字，必须要与方法的一个形参名一致
    @AfterReturning(value = "execution(* AspectJ.Annotation.*.*(..))", returning = "result")
    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        // 获取方法的名字
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LoggingAspect==> The method" + methodName + " end with " + result);
    }

    // 异常通知：在目标方法抛出异常后执行
    // 获取方法的异常：通过throwing来指定一个名字，必须要与方法的一个形参名一致
    // 可以通过形参中异常的类型来设置抛出指定异常来回执行异常通知
    @AfterThrowing(value = "execution(* AspectJ.Annotation.*.*(..))", throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, ArithmeticException ex) {
        // 获取方法的名字
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LoggingAspect==> The method" + methodName + " occurs Exception: " + ex);
    }

    // 环绕通知：环绕着目标方法执行，可以理解为是前四者结合体，更像动态代理的整个过程
    @Around(value = "execution(* AspectJ.Annotation.*.*(..))")
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
