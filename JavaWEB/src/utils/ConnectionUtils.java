package utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtils {

    private static String driver = null;
    private static String url = null ;
    private static String username = null ;
    private static String password = null ;
    private static Properties props = new Properties();

    //ThreadLocal :保证一个线程中只能有一个连接.
    private static ThreadLocal<Connection > tl = new ThreadLocal<>();
    /**
     * 静态的代码块读取db.properties
     */
    static {
        try {
            //类加载器读取文件
            InputStream in = ConnectionUtils.class.getClassLoader().getResourceAsStream("db.properties");
            props.load(in);
            driver =props.getProperty("jdbc.driver");
            url = props.getProperty("jdbc.url");
            username= props.getProperty("jdbc.username");
            password = props.getProperty("jdbc.password");
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接的方法
     */
    public static  Connection  getConn()  throws Exception{
        //先尝试从tl中获取
        Connection conn = tl.get();
        if(conn == null ) {
            conn = DriverManager.getConnection(url, username, password);
            tl.set(conn);
        }
        return conn ;
    }

    /**
     * 关闭连接的方法
     */
    public static void closeConn()  throws Exception{
        //先尝试从tl中获取
        Connection conn = tl.get();
        if(conn != null ) {
            conn.close();
        }
        tl.set(null);
    }

    public static void main(String[] args) throws Exception {
        Connection conn = getConn();
        closeConn();
        Connection conn2 = getConn();
        System.out.println(conn == conn2);
    }
}
