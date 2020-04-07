package Tx_Annotation.Service;

import Tx_Annotation.Dao.BookShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional // 对当前类所有的方法都起作用
@Service
public class BookShopServiceImpl implements BookShopService{

    @Autowired
    private BookShopDao bookShopDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED,/*noRollbackFor = {UserAccountException.class}*/readOnly = false,timeout = 3) // 只对当前的方法起作用
    public void buyBook(String username, String isbn) {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Integer price = bookShopDao.findPriceByIsbn(isbn);
        bookShopDao.updateStock(isbn);
        bookShopDao.updateUserAccount(username,price);
    }

}
