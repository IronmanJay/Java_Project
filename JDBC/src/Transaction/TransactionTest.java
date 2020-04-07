package Transaction;

import file.JDBCUtils;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.sql.*;

public class TransactionTest {

    @Test
    public void testUpdate(){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            String sql1 = "update user_table set balance = balance - 100 where user = ?";
            update(conn,sql1,"AA");
            System.out.println(10/0);
            String sql2 = "update user_table set balance = balance + 100 where user = ?";
            update(conn,sql2,"BB");
            System.out.println("转账成功!");
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    //通用的增删改操作
    public int update(Connection conn, String sql,Object ...args) {//sql中占位符的个数与可变形参的长度相同！
        PreparedStatement ps = null;
        try {
            //2.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);//小心参数声明错误！！
            }
            //4.执行
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //5.资源的关闭
            JDBCUtils.closeResource(null, ps);
        }
        return 0;
    }

    @Test
    public void testTransactionSelect() throws Exception {
        Connection conn =  JDBCUtils.getConnection();
        System.out.println(conn.getTransactionIsolation());
        //conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        conn.setAutoCommit(false);
        String sql = "select user,password,balance from user_table where user = ?";
        User user = getInstance(conn,User.class,sql,"CC");
        System.out.println(user);
    }

    @Test
    public void testTransactionUpdate() throws Exception {
        Connection conn =  JDBCUtils.getConnection();
        conn.setAutoCommit(false);
        String sql = "update user_table set balance = ? where user = ?";
        update(conn,sql,5000,"CC");
        Thread.sleep(15000);
        System.out.println("修改结束");
    }

    //通用的查询操作，用于返回数据表中的一条记录
    public <T> T getInstance(Connection conn, Class<T> clazz,String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            // 获取结果集的元数据 :ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                T t = clazz.newInstance();
                // 处理结果集一行数据中的每一个列
                for (int i = 0; i < columnCount; i++) {
                    // 获取列值
                    Object columValue = rs.getObject(i + 1);
                    // 获取每个列的列名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    // 给t对象指定的columnName属性，赋值为columValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }
}
