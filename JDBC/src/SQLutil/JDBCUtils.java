package SQLutil;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class JDBCUtils {

    //传统方法
    public static Connection getConnection() throws Exception {
        // 1.读取配置文件中的4个基本信息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        // 2.加载驱动
        Class.forName(driverClass);

        // 3.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    //关闭连接和Statement的操作
    public static void closeResource(Connection conn, Statement ps){
        try {
            if(ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //关闭资源操作
    public static void closeResource2(Connection conn, Statement ps, ResultSet rs){
        try {
            if(ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //使用dbutils关闭资源
    public static void closeResource3(Connection conn, Statement ps, ResultSet rs){
        /*try {
            DbUtils.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            DbUtils.close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            DbUtils.close(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        DbUtils.closeQuietly(conn);
        DbUtils.closeQuietly(ps);
        DbUtils.closeQuietly(rs);
    }

    //C3P0
    //数据库连接池只需要提供一个即可
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
    public static Connection getConnection1() throws SQLException {
        Connection conn = cpds.getConnection();
        return conn;
    }

    //DBCP
    private static DataSource source;
    static{
        try {
            Properties pros = new Properties();
            FileInputStream is = new FileInputStream(new File("src/dbcp.properties"));
            pros.load(is);
            source = BasicDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection2() throws Exception {
        Connection conn = source.getConnection();
        return conn;
    }

    //Druid
    private static DataSource source1;
    static {
        try {
            Properties pros = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            pros.load(is);
            source1 = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection3() throws Exception {
        Connection conn = source1.getConnection();
        return conn;
    }
}
