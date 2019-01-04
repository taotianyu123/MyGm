package service.impl;

import dao.impl.sellerDaoImpl;
import pojo.ExtGoods;
import pojo.Goods;
import pojo.Page;
import pojo.Seller;

import java.util.List;

public class sellerServiceDaoImpl implements sellerServiceDao {
    private static sellerServiceDaoImpl ourInstance = new sellerServiceDaoImpl();

    public static sellerServiceDaoImpl getInstance() {
        return ourInstance;
    }

    private sellerServiceDaoImpl() {
    }

    private sellerDaoImpl sellerdao=sellerDaoImpl.getInstance();

    public Seller seLogin(String loginname, String pwd) {
        String sql=" select * from seller where sellerUser=? and sellerPassword=?";
        Object[]parameter={loginname,pwd};
        List<Seller> selist=sellerdao.query(sql,parameter);
        return selist.get(0);
    }

    public Seller queryseid(int id) {
        String sql="select * from seller where id=?";
        Object [] parameter={id};
        List<Seller> selist=sellerdao.query(sql,parameter);
        if (selist!=null&&selist.size()>0) {
            return selist.get(0);
        }
        return null;
    }

    public boolean updatese(Seller se) {
        String sql="UPDATE seller set sellerName=?,sellerUser=?,sellerPassword=?,sellerSex=?,sellerBirthday=?,sellerIdCard=?,sellerEmail=?,sellerTel=?,sellerAddress=? where id=?";
        Object [] parameter={se.getSellerName(),se.getSellerUser(),se.getSellerPassword(),se.getSellerSex(),se.getSellerBirthday(),se.getSellerIdCard(),se.getSellerEmail(),se.getSellerTel(),se.getSellerAddress(),se.getId()};
        int num=sellerdao.update(sql,parameter);
        if(num>0){
            return true;
        }
        return false;
    }

    public Page<ExtGoods> extgPageQueryAll(int pageSize, int pageNumber, int id) {
        Object[] parameter={id};
        return sellerdao.pagegoodsQuery(pageSize,pageNumber,parameter);
    }

    public Page<ExtGoods> godsPageunionQuery(int pageSize, int pageNumber, String Id, String goodsName, String smclassname, int seid) {
        int IdSize = Id.length();
        int goodsNameeSize = goodsName.length();
        int smclassnameSize = smclassname.length();
        if(IdSize == 0 && goodsNameeSize == 0 && smclassnameSize ==0 ){

            String sql1 = "select count(1) from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and se.id=?";
            String sql2 = "select g.id,g.goodsName,sm.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and se.id=?";
            Object [] parameter={seid};
            return sellerdao.nuionpagegoodsQuery(sql1,sql2,pageSize,pageNumber,parameter);


        }else if(IdSize > 0 && goodsNameeSize == 0 && smclassnameSize ==0 ){
            //根据id具体查询
            Object []parameter = {Id,seid};
            String sql1 = "select count(1) from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and g.id=? and se.id=?";
            String sql2 = "select g.id,g.goodsName,sm.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and g.id=? and se.id=?";
            return sellerdao.nuionpagegoodsQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize == 0 && goodsNameeSize > 0 && smclassnameSize ==0){
            //根据姓名模糊查询
            Object []parameter = {"%"+goodsName+"%",seid};
            String sql1 = "select count(1) from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and goodsName LIKE ? and se.id=?";
            String sql2 = "select g.id,g.goodsName,sm.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and goodsName LIKE ? and se.id=?";

            return sellerdao.nuionpagegoodsQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize == 0 && goodsNameeSize == 0 && smclassnameSize > 0){
            //根据性别查询
            Object []parameter = {smclassname,seid};
            String sql1 = "select count(1) from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and goodsSmalId=? and se.id=?";
            String sql2 = "select g.id,g.goodsName,sm.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and goodsSmalId=? and se.id=?";

            return sellerdao.nuionpagegoodsQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize > 0 && goodsNameeSize > 0 && smclassnameSize > 0){
            //根据 姓名 学号 性别 级联查询
            Object []parameter = {Id,"%"+goodsName+"%",smclassname,seid};
            String sql1 = "select count(1) from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and g.id=? and goodsName LIKE ? and goodsSmalId=? and se.id=?";
            String sql2 = "select g.id,g.goodsName,sm.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and g.id=? and goodsName LIKE ? and goodsSmalId=? and se.id=?";
            return sellerdao.nuionpagegoodsQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize > 0 && goodsNameeSize > 0 && smclassnameSize == 0){
            //根据 姓名 学号  级联查询
            Object []parameter = {Id,"%"+goodsName+"%",seid};
            String sql1 = "select count(1) from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and g.id=? and goodsName LIKE ? and se.id=?";
            String sql2 = "select g.id,g.goodsName,sm.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and g.id=? and goodsName LIKE ? and se.id=?";
            return sellerdao.nuionpagegoodsQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize == 0 && goodsNameeSize > 0 && smclassnameSize > 0){
            //根据  姓名 性别 级联查询
            Object []parameter = {"%"+goodsName+"%",smclassname,seid};
            String sql1 = "select count(1) from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and goodsName LIKE ? and goodsSmalId=? and se.id=?";
            String sql2 = "select g.id,g.goodsName,sm.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and and goodsName LIKE ? and goodsSmalId=? and se.id=?";
            return sellerdao.nuionpagegoodsQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize > 0 && goodsNameeSize == 0 && smclassnameSize > 0){
            //根据  学号 性别 级联查询
            Object [] parameter = {Id,smclassname,seid};
            String sql1 = "select count(1) from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and g.id=? and goodsSmalId=? and se.id=?";
            String sql2 = "select g.id,g.goodsName,sm.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and g.id=? and goodsSmalId=? and se.id=?";
            return sellerdao.nuionpagegoodsQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }
        return null;
    }

    public int addgods(Goods gods) {
        String sql="insert into goods (goodsName,goodsSmalId,goodsMoney,goodsNumber,goodsImage,goodsCarriage,goodsType,goodsSeId,goodsDiscId) VALUES(?,?,?,?,?,?,?,?,?)";
        Object [] parameter={gods.getGoodsName(),gods.getGoodsSmalId(),gods.getGoodsMoney(),gods.getGoodsNumber(),gods.getGoodsImage(),gods.getGoodsCarriage(),gods.getGoodsType(),gods.getGoodsSeId(),gods.getGoodsDiscId()};
        return sellerdao.update(sql,parameter);
    }

}
