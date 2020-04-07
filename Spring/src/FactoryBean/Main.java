package FactoryBean;

import DI.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-factorybean.xml");
        Car car = ctx.getBean("car", Car.class);
        System.out.println(car);
    }
}
