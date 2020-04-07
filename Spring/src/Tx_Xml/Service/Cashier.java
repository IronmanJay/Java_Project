package Tx_Xml.Service;

import java.util.List;

public interface Cashier {
    public void checkOut(String username, List<String> isbns);
}
