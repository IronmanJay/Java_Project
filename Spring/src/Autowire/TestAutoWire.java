package Autowire;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAutoWire {

    @Test
    public void testAutoWire() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-autowire.xml");
        Person person = ctx.getBean("person",Person.class);
        System.out.println(person);
    }

}
