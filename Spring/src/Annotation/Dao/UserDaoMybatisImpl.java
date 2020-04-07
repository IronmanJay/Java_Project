package Annotation.Dao;

import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoMybatisImpl implements UserDao{

    @Override
    public void addUser() {
        System.out.println("UserDao mybatis");
    }
}
