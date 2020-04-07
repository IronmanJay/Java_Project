package Annotation.Service;

import Annotation.Dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired(required = false)
    @Qualifier("UseJdbcDaoImpl")
    private UserDao userDao;

//    @Autowired(required = false)
//    @Qualifier("UseJdbcDaoImpl")
//    public void setUserDao(UserDao userDao){
//        this.userDao = userDao;
//    }

    @Override
    public void handleAddUser() {
        // 处理业务
        userDao.addUser();
    }
}
