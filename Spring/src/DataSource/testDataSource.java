package DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

public class testDataSource {

    @Test
    public void test() throws SQLException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-datasourse.xml");
        DataSource ds = ctx.getBean("datasource", DataSource.class);
        System.out.println(ds);
        System.out.println(ds.getConnection());
    }

}
