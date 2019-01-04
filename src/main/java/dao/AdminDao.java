package dao;

import pojo.*;

import java.util.List;

public interface AdminDao {
    List<Superuser> query(String sql);

    List<Superuser> query(String sql,Object[] parameter);

    List<ExtGoods> queryextg(String sql,Object[] parameter);

    List<Seller> sequery(String sql,Object[] parameter);

    List<Customer> cusquery(String sql);

    List<Seller> sequery(String sql);

    List<Discount> disquery(String sql);

    List<Smallclass> smquery(String sql);

    List<Bigclass> bigquery(String sql);

    int pdgoods(String sql,Object[] parameter);

    Page<Customer> pageQuery(int pageSize, int pageNumber);

    Page<Bigclass> pagebigQuery(int pageSize, int pageNumber);

    Page<ExtGoods> pagegoodsQuery(int pageSize, int pageNumber);

    Page<ExtSmallclass> pagesmQuery(int pageSize, int pageNumber);

    Page<Announcement> pageannQuery(int pageSize, int pageNumber);

    Page<Seller> pageseQuery(int pageSize, int pageNumber);

    Page<Customer> nuionpageQuery(String sql1,String sql2,int pageSize, int pageNumber,Object[] parameter);

    Page<ExtGoods> nuionpagegoodsQuery(String sql1,String sql2,int pageSize, int pageNumber,Object[] parameter);

    Page<ExtSmallclass> nuionpagesmQuery(String sql1,String sql2,int pageSize, int pageNumber,Object[] parameter);

    Boolean delcus(int id);

    Boolean delbig(int id);

    Boolean delann(int id);

    int update(String sql, Object[] para);

    List<Customer> cusquery(String sql,Object[] parameter);

    List<Bigclass> bigquery(String sql,Object[] parameter);

    List<Announcement> annquery(String sql,Object[] parameter);

    List<ExtSmallclass> smquery(String sql,Object[] parameter);
}
