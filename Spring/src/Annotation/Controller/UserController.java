package Annotation.Controller;

import Annotation.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // (value = "uc")
public class UserController {

    @Autowired // 自动装配
    private UserService userService;

    public void regist(){
        userService.handleAddUser();
    }
}
