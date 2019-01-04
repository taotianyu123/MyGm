package dao.impl;

import core.util.PageUtil;
import dao.AdminDao;
import dao.BaseDao;
import pojo.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl extends BaseDao implements AdminDao {
    private static AdminDaoImpl ourInstance = new AdminDaoImpl();

    public static AdminDaoImpl getInstance() {
        return ourInstance;
    }

    private AdminDaoImpl() {
    }

     public List<Superuser> query(String sql) {
            ResultSet rs=getQuery(sql,null);
            List<Superuser> suplist=new ArrayList<Superuser>();
            try {
                while(rs.next()){
                    Superuser sup=new Superuser();
                    sup.setId(rs.getLong("id"));
                    sup.setUserName(rs.getString("userName"));
                    sup.setUserPassword(rs.getString("userImage"));
                    sup.setUserStatus(rs.getInt("userStatus"));
                    sup.setUserId("userID");
                    sup.setUserLoginName(rs.getString("userLoginName"));
                    suplist.add(sup);
                }
                return suplist;
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                dbClose();
            }
            return null;
        }

        public List<Superuser> query(String sql, Object[] parameter) {
            ResultSet rs=getQuery(sql,parameter);
            List<Superuser> suplist=new ArrayList<Superuser>();
            try {
                while(rs.next()){
                    Superuser sup=new Superuser();
                    sup.setId(rs.getLong("id"));
                    sup.setUserName(rs.getString("userName"));
                    sup.setUserPassword(rs.getString("userImage"));
                    sup.setUserStatus(rs.getInt("userStatus"));
                    sup.setUserId(rs.getString("userID"));
                    sup.setUserLoginName(rs.getString("userLoginName"));
                    suplist.add(sup);
                }
                return suplist;
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                dbClose();
            }
            return null;
        }

    public List<ExtGoods> queryextg(String sql, Object[] parameter) {
        ResultSet rs=getQuery(sql,parameter);
        List<ExtGoods> extglist=new ArrayList<ExtGoods>();
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
        return extglist;
    }

    public List<Seller> sequery(String sql, Object[] parameter) {
        ResultSet rs=getQuery(sql,parameter);
        List<Seller> selist=new ArrayList<Seller>();
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

    public List<Customer> cusquery(String sql) {
        ResultSet rs=getQuery(sql,null);
        List<Customer> cuslist=new ArrayList<Customer>();
        try {
            while(rs.next()){
                Customer cus=new Customer();
                cus.setId(rs.getLong("id"));
                cus.setCusName(rs.getString("cusName"));
                cus.setCusLoginName(rs.getString("cusLoginName"));
                cus.setCusPassword(rs.getString("cusPassword"));
                cus.setCusEmail(rs.getString("cusEmail"));
                cus.setCusSex(rs.getString("cusSex"));
                cus.setCusPhoto(rs.getString("cusPhoto"));
                cus.setCusHobby(rs.getString("cusHobby"));
                cus.setCusCode(rs.getString("cusCode"));
                cus.setCusBirthday(rs.getDate("cusBirthday"));
                cuslist.add(cus);
            }
            return cuslist;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return null;
    }

    public List<Seller> sequery(String sql) {
       ResultSet rs=getQuery(sql,null);
       List<Seller> selist=new ArrayList<Seller>();
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

    public List<Discount> disquery(String sql) {
        ResultSet rs=getQuery(sql,null);
        List<Discount> dislist=new ArrayList<Discount>();
        try{
            while(rs.next()){
                Discount dis = new Discount();
                dis.setId(rs.getLong("id"));
                dis.setDiscRate(rs.getDouble("discRate"));
                dislist.add(dis);
            }
            return dislist;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return null;
    }

    public List<Smallclass> smquery(String sql) {
        List<Smallclass> smlist=new ArrayList<Smallclass>();
        ResultSet rs=getQuery(sql,null);
        try{
            while(rs.next()){
                Smallclass sm = new Smallclass();
                sm.setId(rs.getLong("id"));
                sm.setSmallName(rs.getString("smallName"));
                sm.setSmallBigId(rs.getLong("smallBigId"));
                sm.setSmallText(rs.getString("smallText"));
                smlist.add(sm);
            }
            return smlist;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return null;
    }

    public List<Bigclass> bigquery(String sql) {
        ResultSet rs=getQuery(sql,null);
        List<Bigclass> biglist=new ArrayList<Bigclass>();
        try{
            while(rs.next()){
                Bigclass big = new Bigclass();
                big.setId(rs.getLong("id"));
                big.setBigName(rs.getString("bigName"));
                big.setBigText(rs.getString("bigText"));
                biglist.add(big);
            }
            return biglist;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return null;
    }

    public int pdgoods(String sql, Object[] parameter) {
        ResultSet rs=getQuery(sql,parameter);
        List<Goods> goodsList=new ArrayList<Goods>();
        try{
            while(rs.next()){
                Goods g = new Goods();
                g.setId(rs.getLong("id"));
                g.setGoodsName(rs.getString("goodsName"));
                g.setGoodsSmalId(rs.getLong("goodsSmalId"));
                g.setGoodsMoney(rs.getDouble("goodsMoney"));
                g.setGoodsNumber(rs.getLong("goodsNumber"));
                g.setGoodsImage(rs.getString("goodsImage"));
                g.setGoodsCarriage(rs.getDouble("goodsCarriage"));
                g.setGoodsType(rs.getLong("goodsType"));
                g.setGoodsSeId(rs.getLong("goodsSeId"));
                g.setGoodsDiscId(rs.getLong("goodsDiscId"));
                goodsList.add(g);
            }
            return goodsList.size();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return 0;
    }

    public List<Customer> cusquery(String sql,Object[] parameter) {
        ResultSet rs=getQuery(sql,parameter);
        List<Customer> cuslist=new ArrayList<Customer>();
        try {
            while(rs.next()){
                Customer cus=new Customer();
                cus.setId(rs.getLong("id"));
                cus.setCusName(rs.getString("cusName"));
                cus.setCusLoginName(rs.getString("cusLoginName"));
                cus.setCusPassword(rs.getString("cusPassword"));
                cus.setCusEmail(rs.getString("cusEmail"));
                cus.setCusSex(rs.getString("cusSex"));
                cus.setCusPhoto(rs.getString("cusPhoto"));
                cus.setCusHobby(rs.getString("cusHobby"));
                cus.setCusCode(rs.getString("cusCode"));
                cus.setCusBirthday(rs.getDate("cusBirthday"));
                cuslist.add(cus);
            }
            return cuslist;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return null;
    }

    public List<Bigclass> bigquery(String sql, Object[] parameter) {
        List<Bigclass> biglist=new ArrayList<Bigclass>();
        ResultSet rs=getQuery(sql,parameter);
        try {
            while(rs.next()){
                Bigclass big=new Bigclass();
                big.setId(rs.getLong("id"));
                big.setBigName(rs.getString("bigName"));
                big.setBigText(rs.getString("bigText"));
                biglist.add(big);
            }
            return biglist;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return null;
    }

    public List<Announcement> annquery(String sql, Object[] parameter) {
        List<Announcement> annlist=new ArrayList<Announcement>();
        ResultSet rs=getQuery(sql,parameter);
        try {
            while(rs.next()){
                Announcement ann=new Announcement();
                ann.setId(rs.getLong("id"));
                ann.setAnTitle(rs.getString("anTitle"));
                ann.setAnText(rs.getString("anText"));
                ann.setAnDate(rs.getDate("anDate"));
                annlist.add(ann);
            }
            return annlist;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return null;
    }

    public List<ExtSmallclass> smquery(String sql, Object[] parameter) {
        List<ExtSmallclass> smlist=new ArrayList<ExtSmallclass>();
        ResultSet rs=getQuery(sql,parameter);
        try{
            while(rs.next()){
                ExtSmallclass exts=new ExtSmallclass();
                exts.setId(rs.getLong("id"));
                exts.setSmallName(rs.getString("smallName"));
                exts.setSmallBigId(rs.getLong("smallBigId"));
                exts.setSmallText(rs.getString("smallText"));
                Bigclass b=new Bigclass();
                b.setBigName(rs.getString("bigName"));
                exts.setBigclass(b);
                smlist.add(exts);
            }
            return smlist;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return null;
    }

    public Page<Customer> pageQuery(int pageSize, int pageNumber) {
        Page<Customer> page = new Page<Customer>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        String sql1 = "select count(1) from customer";
        String sql2 = "select * from customer";
        page.setTotalRecode(PageUtil.getTotalRecode(sql1,null));
        List<Customer> cuslist = new ArrayList<Customer>();
        ResultSet rs = PageUtil.getPageDate(sql2,pageSize,pageNumber,null);
        try{
            while(rs.next()){
                Customer cus=new Customer();
                cus.setId(rs.getLong("id"));
                cus.setCusName(rs.getString("cusName"));
                cus.setCusLoginName(rs.getString("cusLoginName"));
                cus.setCusPassword(rs.getString("cusPassword"));
                cus.setCusEmail(rs.getString("cusEmail"));
                cus.setCusSex(rs.getString("cusSex"));
                cus.setCusPhoto(rs.getString("cusPhoto"));
                cus.setCusHobby(rs.getString("cusHobby"));
                cus.setCusCode(rs.getString("cusCode"));
                cus.setCusBirthday(rs.getDate("cusBirthday"));
                cuslist.add(cus);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        page.setPageDate(cuslist);
        return page;
    }

    public Page<Bigclass> pagebigQuery(int pageSize, int pageNumber) {
        Page<Bigclass> page = new Page<Bigclass>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        String sql1 = "select count(1) from bigclass";
        String sql2 = "select * from bigclass";
        page.setTotalRecode(PageUtil.getTotalRecode(sql1,null));
        List<Bigclass> biglist = new ArrayList<Bigclass>();
        ResultSet rs = PageUtil.getPageDate(sql2,pageSize,pageNumber,null);
        try{
            while(rs.next()){
                Bigclass big=new Bigclass();
                big.setId(rs.getLong("id"));
                big.setBigName(rs.getString("bigName"));
                big.setBigText(rs.getString("bigText"));
                biglist.add(big);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        page.setPageDate(biglist);
        return page;
    }

    public Page<ExtGoods> pagegoodsQuery(int pageSize, int pageNumber) {
        Page<ExtGoods> page = new Page<ExtGoods>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        String sql1 = "select count(1) from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id";
        String sql2 = "select g.id,g.goodsName,sm.smallName,g.goodsSmalId,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,g.goodsSeId,g.goodsDiscId,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id";
        page.setTotalRecode(PageUtil.getTotalRecode(sql1,null));
        List<ExtGoods> extglist = new ArrayList<ExtGoods>();
        ResultSet rs = PageUtil.getPageDate(sql2,pageSize,pageNumber,null);
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

    public Page<ExtSmallclass> pagesmQuery(int pageSize, int pageNumber) {
        Page<ExtSmallclass> page = new Page<ExtSmallclass>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        String sql1 = "select count(1) from smallclass sm,bigclass b where sm.smallBigId=b.id";
        String sql2 = "select sm.id,sm.smallName,sm.smallBigId,sm.smallText,b.bigName from smallclass sm,bigclass b where sm.smallBigId=b.id";
        page.setTotalRecode(PageUtil.getTotalRecode(sql1,null));
        List<ExtSmallclass> extslist = new ArrayList<ExtSmallclass>();
        ResultSet rs = PageUtil.getPageDate(sql2,pageSize,pageNumber,null);
        try{
            while(rs.next()){
                ExtSmallclass exts=new ExtSmallclass();
                exts.setId(rs.getLong("id"));
                exts.setSmallName(rs.getString("smallName"));
                exts.setSmallBigId(rs.getLong("smallBigId"));
                exts.setSmallText(rs.getString("smallText"));
                Bigclass b=new Bigclass();
                b.setBigName(rs.getString("bigName"));
                exts.setBigclass(b);
                extslist.add(exts);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        page.setPageDate(extslist);
        return page;
    }

    public Page<Announcement> pageannQuery(int pageSize, int pageNumber) {
        Page<Announcement> page = new Page<Announcement>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        String sql1 = "select count(1) from announcement";
        String sql2 = "select * from announcement";
        page.setTotalRecode(PageUtil.getTotalRecode(sql1,null));
        List<Announcement> annlist = new ArrayList<Announcement>();
        ResultSet rs = PageUtil.getPageDate(sql2,pageSize,pageNumber,null);
        try{
            while(rs.next()){
                Announcement ann=new Announcement();
                ann.setId(rs.getLong("id"));
                ann.setAnTitle(rs.getString("anTitle"));
                ann.setAnText(rs.getString("anText"));
                ann.setAnDate(rs.getDate("anDate"));
                annlist.add(ann);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        page.setPageDate(annlist);
        return page;
    }

    public Page<Seller> pageseQuery(int pageSize, int pageNumber) {
        Page<Seller> page = new Page<Seller>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        String sql1 = "select count(1) from seller";
        String sql2 = "select * from seller";
        page.setTotalRecode(PageUtil.getTotalRecode(sql1,null));
        List<Seller> selist = new ArrayList<Seller>();
        ResultSet rs = PageUtil.getPageDate(sql2,pageSize,pageNumber,null);
        try{
            while(rs.next()){
                Seller se=new Seller();
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
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        page.setPageDate(selist);
        return page;
    }

    public Page<Customer> nuionpageQuery(String sql1, String sql2, int pageSize, int pageNumber, Object[] parameter) {
        Page<Customer> page=new Page<Customer>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        page.setTotalRecode(PageUtil.getTotalRecode(sql1,parameter));
        List<Customer> cuslist=new ArrayList<Customer>();
        ResultSet rs=PageUtil.getPageDate(sql2,pageSize,pageNumber,parameter);
        try{
            while(rs.next()){
                Customer cus=new Customer();
                cus.setId(rs.getLong("id"));
                cus.setCusName(rs.getString("cusName"));
                cus.setCusLoginName(rs.getString("cusLoginName"));
                cus.setCusPassword(rs.getString("cusPassword"));
                cus.setCusEmail(rs.getString("cusEmail"));
                cus.setCusSex(rs.getString("cusSex"));
                cus.setCusPhoto(rs.getString("cusPhoto"));
                cus.setCusHobby(rs.getString("cusHobby"));
                cus.setCusCode(rs.getString("cusCode"));
                cus.setCusBirthday(rs.getDate("cusBirthday"));
                cuslist.add(cus);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        page.setPageDate(cuslist);
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

    public Page<ExtSmallclass> nuionpagesmQuery(String sql1, String sql2, int pageSize, int pageNumber, Object[] parameter) {
        Page<ExtSmallclass> page=new Page<ExtSmallclass>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        page.setTotalRecode(PageUtil.getTotalRecode(sql1,parameter));
        List<ExtSmallclass> smlist=new ArrayList<ExtSmallclass>();
        ResultSet rs=PageUtil.getPageDate(sql2,pageSize,pageNumber,parameter);
        try{
            while(rs.next()){
                ExtSmallclass exts=new ExtSmallclass();
                exts.setId(rs.getInt("id"));
                exts.setSmallName(rs.getString("smallName"));
                exts.setSmallBigId(rs.getLong("smallBigId"));
                exts.setSmallText(rs.getString("smallBigId"));
                Bigclass b=new Bigclass();
                b.setBigName(rs.getString("bigName"));
                exts.setBigclass(b);
                smlist.add(exts);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
        page.setPageDate(smlist);
        return page;
    }

    public Boolean delcus(int id) {
        String sql="delete from customer where id=?";
        Object [] parameter={id};
        int num=getUpdate(sql,parameter);
        if (num>0){
            return true;
        }
        return false;
    }

    public Boolean delbig(int id) {
        String sql="delete from bigclass where id not in(select smallBigId from smallclass GROUP BY smallBigId) and id=?";
        Object [] parameter={id};
        int num=getUpdate(sql,parameter);
        if (num>0){
            return true;
        }
        return false;
    }

    public Boolean delann(int id) {
        String sql="delete from announcement where id=?";
        Object [] parameter={id};
        int num=getUpdate(sql,parameter);
        if (num>0){
            return true;
        }
        return false;
    }

    public int update(String sql, Object[] para) {
        return getUpdate(sql,para);
    }


}
