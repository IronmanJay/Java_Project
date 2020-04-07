package Servlet;

import Dao.beans.Person;
import org.springframework.context.ApplicationContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 访问SpringIOC容器中的person对象
        // 从ServletContext对象中获取SpringIOC容器对象
        ServletContext sc = getServletContext();
        ApplicationContext ctx = (ApplicationContext) sc.getAttribute("applicationContext");
        Person person = ctx.getBean("person",Person.class);
        person.sayHello();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
