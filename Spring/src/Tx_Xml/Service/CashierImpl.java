package Tx_Xml.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashierImpl implements Cashier {

    @Autowired
    private BookShopService bookShopService;

    public void checkOut(String username, List<String> isbns) {
        for(String isbn : isbns){
            bookShopService.buyBook(username,isbn);
        }
    }

}
