package Helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 请求处理器/控制器
@Controller
public class SpringMVCHandler {

    // 处理客户端的请求 http://localhost:8080/SpringMVC/hello
    @RequestMapping(value = "/hello") // 请求映射
    public String handleHello(){
        System.out.println("Hello Springmvc");
        return "success"; // 通过视图解析器解析的到具体的视图，再转发去往该视图
    }

}
