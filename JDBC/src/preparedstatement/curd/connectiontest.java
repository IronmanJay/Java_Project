package preparedstatement.curd;

import com.mysql.jdbc.Driver;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class connectiontest {
    // 方式一
    @Test
    public void connectiontest1() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "990929");
        Connection conn = driver.connect(url, info);
        System.out.println(conn);
    }
    // 方式二
    @Test
    public void connectiontest2() throws Exception{
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "990929");
        Connection conn = driver.connect(url, info);
        System.out.println(conn);
    }
    //方式三
    @Test
    public void connectiontest3() throws Exception{
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "990929";
        DriverManager.registerDriver(driver);
        Connection conn =  DriverManager.getConnection(url,user,password);
        System.out.println(conn);
    }
    //方式四
    @Test
    public void connectiontest4() throws Exception{
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "990929";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url,user,password);
        System.out.println(conn);
    }
    //方式五
    @Test
    public void connectiontest5() throws Exception{
        InputStream is =  connectiontest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros =  new Properties();
        pros.load(is);
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");
        Class.forName(driverClass);
        Connection conn = DriverManager.getConnection(url,user,password);
        System.out.println(conn);
    }
}
