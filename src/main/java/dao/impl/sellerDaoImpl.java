package dao.impl;

import core.util.PageUtil;
import dao.BaseDao;
import dao.sellerDao;
import pojo.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class sellerDaoImpl extends BaseDao implements sellerDao {
    private static sellerDaoImpl ourInstance = new sellerDaoImpl();

    public static sellerDaoImpl getInstance() {
        return ourInstance;
    }

    private sellerDaoImpl() {
    }

    public List<Seller> query(String sql, Object[] parameter) {
        List<Seller> selist=new ArrayList<Seller>();
        ResultSet rs=getQuery(sql,parameter);
        try{
            while(rs.next()){
                Seller se = new Seller();
                se.setId(rs.getLong("id"));
                se.setSellerName(rs.getString("sellerName"));
                se.setSellerUser(rs.getString("sellerUser"));
                se.setSellerPassword(rs.getString("sellerPassword"));
                se.setSellerSex(rs.getString("sellerSex"));
                se.setSellerBirthday(rs.getDate("sellerBirthday"));
                se.setSellerIdCard(rs.getString("sellerIdCard"));
                se.setSellerEmail(rs.getString("sellerEmail"));
                se.setSellerTel(rs.getString("sellerTel"));
                se.setSellerAddress(rs.getString("sellerAddress"));
                selist.add(se);
            }
            return selist;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return null;
    }

    public int update(String sql, Object[] parameter) {
        int num=getUpdate(sql,parameter);
        return num;
    }

    public Page<ExtGoods> pagegoodsQuery(int pageSize, int pageNumber,Object [] parameter) {
        Page<ExtGoods> page = new Page<ExtGoods>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        String sql1 = "select count(1) from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and se.id=?";
        String sql2 = "select g.id,g.goodsName,sm.smallName,g.goodsSmalId,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,g.goodsSeId,g.goodsDiscId,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and se.id=?";
        page.setTotalRecode(PageUtil.getTotalRecode(sql1,parameter));
        List<ExtGoods> extglist = new ArrayList<ExtGoods>();
        ResultSet rs = PageUtil.getPageDate(sql2,pageSize,pageNumber,parameter);
        try{
            while(rs.next()){
                ExtGoods extg=new ExtGoods();
                extg.setId(rs.getLong("id"));
                extg.setGoodsName(rs.getString("goodsName"));
                Smallclass sm=new Smallclass();
                sm.setSmallName(rs.getString("smallName"));
                extg.setSmallclass(sm);
                extg.setGoodsSmalId(rs.getLong("goodsSmalId"));
                extg.setGoodsMoney(rs.getLong("goodsMoney"));
                extg.setGoodsNumber(rs.getLong("goodsNumber"));
                extg.setGoodsImage(rs.getString("goodsImage"));
                extg.setGoodsCarriage(rs.getLong("goodsCarriage"));
                extg.setGoodsType(rs.getLong("goodsType"));
                extg.setGoodsSeId(rs.getLong("goodsSeId"));
                extg.setGoodsDiscId(rs.getLong("goodsDiscId"));
                Seller se=new Seller();
                se.setSellerName(rs.getString("sellerName"));
                extg.setSeller(se);
                Discount dis=new Discount();
                dis.setDiscRate(rs.getDouble("discRate"));
                extg.setDiscount(dis);
                extglist.add(extg);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        page.setPageDate(extglist);
        return page;
    }

    public Page<ExtGoods> nuionpagegoodsQuery(String sql1, String sql2, int pageSize, int pageNumber, Object[] parameter) {
        Page<ExtGoods> page=new Page<ExtGoods>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        page.setTotalRecode(PageUtil.getTotalRecode(sql1,parameter));
        List<ExtGoods> goodslist=new ArrayList<ExtGoods>();
        ResultSet rs=PageUtil.getPageDate(sql2,pageSize,pageNumber,parameter);
        try{
            while(rs.next()){
                ExtGoods extg=new ExtGoods();
                extg.setId(rs.getLong("id"));
                extg.setGoodsName(rs.getString("goodsName"));
                Smallclass sm=new Smallclass();
                sm.setSmallName(rs.getString("smallName"));
                extg.setSmallclass(sm);
                extg.setGoodsMoney(rs.getLong("goodsMoney"));
                extg.setGoodsNumber(rs.getLong("goodsNumber"));
                extg.setGoodsImage(rs.getString("goodsImage"));
                extg.setGoodsCarriage(rs.getLong("goodsCarriage"));
                extg.setGoodsType(rs.getLong("goodsType"));
                Seller se=new Seller();
                se.setSellerName(rs.getString("sellerName"));
                extg.setSeller(se);
                Discount dis=new Discount();
                dis.setDiscRate(rs.getLong("discRate"));
                extg.setDiscount(dis);
                goodslist.add(extg);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        page.setPageDate(goodslist);
        return page;
    }

}
