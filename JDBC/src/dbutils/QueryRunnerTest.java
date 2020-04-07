package dbutils;

import SQLutil.JDBCUtils;
import bean.Customer;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class QueryRunnerTest {

    @Test
    public void testInsert(){
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "insert into customers(name,email,birth)values(?,?,?)";
            int insertcount = runner.update(conn,sql,"蔡徐坤","cai@qq.com","2020-02-02");
            System.out.println("添加了"+insertcount+"条记录");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @Test
    public void testQquery1(){
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id = ?";
            BeanHandler<Customer> handler= new BeanHandler<>(Customer.class);
            Customer customer = runner.query(conn,sql,handler,20);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @Test
    public void testQquery2(){
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id < ?";
            BeanListHandler<Customer> handler = new BeanListHandler<>(Customer.class);
            List<Customer> list = runner.query(conn,sql,handler,20);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @Test
    public void testQquery3(){
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id < ?";
            MapHandler handler = new MapHandler();
            Map<String,Object> map = runner.query(conn,sql,handler,20);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @Test
    public void testQquery4(){
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id < ?";
            MapListHandler handler = new MapListHandler();
            List<Map<String,Object>> list = runner.query(conn,sql,handler,20);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @Test
    public void testQquery5(){
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select count(*) from customers";
            ScalarHandler handler = new ScalarHandler();
            Long count = (long) runner.query(conn,sql,handler);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @Test
    public void testQquery6(){
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select max(birth) from customers";
            ScalarHandler handler = new ScalarHandler();
            Date maxBirth = (Date) runner.query(conn,sql,handler);
            System.out.println(maxBirth);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @Test
    public void testQquery7(){
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id = ?";
            ResultSetHandler<Customer> handler = new ResultSetHandler<Customer>() {
                @Override
                public Customer handle(ResultSet resultSet) throws SQLException {
                    if(resultSet.next()){
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String email = resultSet.getString("email");
                        Date birth = resultSet.getDate("birth");
                        Customer customer = new Customer(id,name,email,birth);
                        return customer;
                    }
                    return null;
                }
            };
            Customer customer = runner.query(conn,sql,handler,20);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }
}
