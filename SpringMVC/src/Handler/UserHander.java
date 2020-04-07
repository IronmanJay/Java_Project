package Handler;

import Dao.beans.Person;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

@Controller
public class UserHander {

    @Autowired
    private UserService userService;

    public UserHander(){
        System.out.println("UserHander");
    }

    @RequestMapping(value = "/hello")
    public String hello(HttpSession session){
        userService.hello();
        ServletContext sc = session.getServletContext();
        // SpringIOC容器对象
        //ApplicationContext ctx = (ApplicationContext)sc.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        // 工具类
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
        Person person = ctx.getBean("person",Person.class);
        person.sayHello();
        return "success";
    }

}
