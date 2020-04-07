package Annotation.Dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserJdbcDaoImpl implements UserDao {
    @Override
    public void addUser() {
        System.out.println("UserDao jdbc");
    }
}
