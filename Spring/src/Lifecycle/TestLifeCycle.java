package Lifecycle;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestLifeCycle {

    @Test
    public void testLifeCycle(){
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring-lifecycle.xml");
        Car car = ctx.getBean("car",Car.class);
        System.out.println("第四个阶段，使用bean对象：" + car);
        // 关闭容器
        ctx.close();
    }

}
