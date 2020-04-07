package Tx_Xml.Test;

import Tx_Xml.Dao.BookShopDao;
import Tx_Xml.Service.BookShopService;
import Tx_Xml.Service.Cashier;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.ArrayList;
import java.util.List;

public class TestTransaction {

    private BookShopDao bookShopDao;
    private BookShopService bookShopService;
    private Cashier cashier;

    @Before
    public void init(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-jdbc_book_xml.xml");
        bookShopDao = ctx.getBean("bookShopDaoImpl", BookShopDao.class);
        bookShopService = ctx.getBean("bookShopServiceImpl", BookShopService.class);
        cashier = ctx.getBean("cashierImpl", Cashier.class);
        System.out.println(bookShopService.getClass().getName());
    }

    @Test
    public void testTx(){
        bookShopService.buyBook("Tom","1001");
    }

    @Test
    public void testCheckOut(){
        List<String> isbns = new ArrayList<>();
        isbns.add("1001");
        isbns.add("1002");
        cashier.checkOut("Tom",isbns);
    }

}
