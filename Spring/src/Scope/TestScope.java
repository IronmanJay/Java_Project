package Scope;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestScope {

    @Test
    public void testScope(){
        // 创建了IOC容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-scope.xml");
        Car car1 = ctx.getBean("car",Car.class);
        Car car2 = ctx.getBean("car",Car.class);
        System.out.println(car1==car2);
    }

}
