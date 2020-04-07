package Listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;

@WebListener()
public class MyServletContextListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public void contextInitialized(ServletContextEvent sce) {

    }

    // 当监听到ServletContext被创建，则执行该方法
    public void contextDestroyed(ServletContextEvent sce) {
        // 1、创建SpringIOC容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2、将SpringIOC容器对象绑定到ServletContext中
        ServletContext sc = sce.getServletContext();
        sc.setAttribute("applicationContext",ctx);
    }

}
