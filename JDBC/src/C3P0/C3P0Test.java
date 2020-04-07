package C3P0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Test {

    //方式一
    @Test
    public void testConnection1() throws Exception {
        //获取C3P0数据库连接池
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.jdbc.Driver" );
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/test" );
        cpds.setUser("root");
        cpds.setPassword("990929");

        //设置初始时数据库连接池中的链接数
        cpds.setInitialPoolSize(10);

        Connection conn = cpds.getConnection();
        System.out.println(conn);

        //销毁C3P0数据库连接池
        DataSources.destroy(cpds);
    }

    //方式二：使用配置文件
    @Test
    public void testConnection2() throws SQLException{
        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
        Connection conn = cpds.getConnection();
        System.out.println(conn);
    }
}
