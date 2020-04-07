package DI;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDI {

    @Test
    public void testSet(){
        // 1、创建IOC容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-di.xml");
        Car car = ctx.getBean("car",Car.class);
        System.out.println(car);
    }

    @Test
    public void testConstructor(){
        // 1、创建IOC容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-di.xml");
        Car car = ctx.getBean("car1",Car.class);
        System.out.println(car);
    }

    @Test
    public void testP(){
        // 1、创建IOC容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-di.xml");
        Car car = ctx.getBean("car2",Car.class);
        System.out.println(car);
    }
    @Test
    public void testZML(){
        // 1、创建IOC容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-di.xml");
        Book book = ctx.getBean("book",Book.class);
        System.out.println(book);
    }

    @Test
    public void testRef(){
        // 1、创建IOC容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-di.xml");
        Person person = ctx.getBean("person",Person.class);
        System.out.println(person);
    }

    private ApplicationContext ctx = null;

    @Before
    public void init(){
        // 1、创建IOC容器对象
        ctx = new ClassPathXmlApplicationContext("spring-di.xml");
    }

    @Test
    public void testInnerBean(){
        Person person = ctx.getBean("person1",Person.class);
        System.out.println(person);
    }

    @Test
    public void testNull(){
        Person person = ctx.getBean("person2",Person.class);
        System.out.println(person);
    }

    @Test
    public void testList(){
        PersonList personList = ctx.getBean("personlist",PersonList.class);
        System.out.println(personList);
    }

    @Test
    public void testMap(){
        PersonMap personMap = ctx.getBean("personMap",PersonMap.class);
        System.out.println(personMap);
    }
}
