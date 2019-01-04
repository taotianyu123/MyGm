package service;

import pojo.ExtGoods;
import pojo.Goods;
import pojo.Page;
import pojo.Seller;

public interface sellerServiceDao {
    Seller seLogin(String loginname, String pwd);

    Seller queryseid(int id);

    boolean updatese(Seller se);

    Page<ExtGoods> extgPageQueryAll(int pageSize, int pageNumber,int id);

    Page<ExtGoods> godsPageunionQuery(int pageSize,int pageNumber,String Id, String goodsName, String smclassname,int seid);

    int addgods(Goods gods);

}
