package AspectJ.Annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {


    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-aspectJ_annotation.xml");
        ArithmeticCalculator ac = ctx.getBean("arithmeticCalculatorImpl",ArithmeticCalculator.class);
        System.out.println(ac.getClass().getName());
        int result = ac.add(1,1);
        System.out.println("Main Reault：" + result);
//        int result1 = ac.div(5,0);
//        System.out.println("Main Reault：" + result1);
    }

}
