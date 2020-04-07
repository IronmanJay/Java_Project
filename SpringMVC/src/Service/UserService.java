package Service;

import Dao.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserService(){
        System.out.println("UserService");
    }

    public void hello(){
        userDao.hello();
    }

}
