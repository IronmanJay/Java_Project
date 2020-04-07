package Tx_Xml.Service;

import Tx_Xml.Dao.BookShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookShopServiceImpl implements BookShopService {

    @Autowired
    private BookShopDao bookShopDao;

    public void buyBook(String username, String isbn) {
        Integer price = bookShopDao.findPriceByIsbn(isbn);
        bookShopDao.updateStock(isbn);
        bookShopDao.updateUserAccount(username,price);
    }

}
