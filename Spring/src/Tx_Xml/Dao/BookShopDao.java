package Tx_Xml.Dao;

public interface BookShopDao {

    // 根据书号查询书的价格
    public int findPriceByIsbn(String isbn);

    // 更新书的库存
    public void updateStock(String isbn);

    // 更新用户的余额
    public void updateUserAccount(String username, Integer price);

}
