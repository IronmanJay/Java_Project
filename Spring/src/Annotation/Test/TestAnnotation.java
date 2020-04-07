package Annotation.Test;

import org.junit.jupiter.api.Test;
import Annotation.Controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnnotation {

    @Test
    public void testAnnotation(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-annotation.xml");
        UserController uc = ctx.getBean("userController",UserController.class);
        System.out.println("UserController" + uc);
//        UserService us = ctx.getBean("userServiceImpl",UserService.class);
//        System.out.println("UserService" + us);
//        UserDao ud = ctx.getBean("userJdbcDaoImpl",UserDao.class);
//        System.out.println("UserDao" + ud);
        uc.regist();
    }

}
