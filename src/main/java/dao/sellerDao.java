package dao;

import pojo.ExtGoods;
import pojo.Page;
import pojo.Seller;

import java.util.List;

public interface sellerDao {
    List<Seller> query(String sql, Object[] parameter);

    int update(String sql,Object[] parameter);

    Page<ExtGoods> pagegoodsQuery(int pageSize, int pageNumber,Object [] parameter);

    Page<ExtGoods> nuionpagegoodsQuery(String sql1,String sql2,int pageSize, int pageNumber,Object[] parameter);

}
