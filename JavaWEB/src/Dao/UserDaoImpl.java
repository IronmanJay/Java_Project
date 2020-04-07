package Dao;

import Login_Beans.User;
import utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {
    public User getUserByUsernameAndPassword(String username, String password) {
        User u = null ;
        //JDBC: 获取连接  编写SQL 预编译SQL 设置参数  执行SQL 封装结果  关闭连接
        // 获取连接
        try {
			Connection conn = ConnectionUtils.getConn();
            String sql ="select id, username,password from tb1_user where username = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet  rs = ps.executeQuery();
            if(rs.next()) {
                u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
            }
            return u;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionUtils.closeConn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public User getUserByUsername(String username) {
        User u = null ;
        try {
            Connection conn = ConnectionUtils.getConn();
            String sql ="select id, username,password from tb1_user where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet  rs = ps.executeQuery();
            if(rs.next()) {
                u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
            }
            return u;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionUtils.closeConn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void insertUser(String username, String password) {
        try {
            Connection conn = ConnectionUtils.getConn();
            String sql = "insert into tb1_user (username,password) values(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                ConnectionUtils.closeConn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
