package HelloWorld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        // 获取到Person对象
        // 1、创建Spring的IOC容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2、获取Person对象
        //Person person = (Person) ctx.getBean("person");
        //Person person = ctx.getBean(Person.class);
        Person person = ctx.getBean("person",Person.class);
        // 3、调用方法
        person.sayHello();
    }

}
