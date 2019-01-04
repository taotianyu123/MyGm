package service.impl;

import dao.impl.AdminDaoImpl;
import pojo.*;
import service.AdminServiceDao;

import javax.jws.Oneway;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceDaoImpl implements AdminServiceDao {
    private static AdminServiceDaoImpl ourInstance = new AdminServiceDaoImpl();

    public static AdminServiceDaoImpl getInstance() {
        return ourInstance;
    }

    private AdminServiceDaoImpl() {
    }

    private AdminDaoImpl adminDao=AdminDaoImpl.getInstance();


    public Superuser suoLogin(String loginname, String pwd) {
        String sql="select * from superuser where userLoginName=? and userPassword=?";
        Object [] parameter={loginname,pwd};
        List<Superuser> suplist=adminDao.query(sql,parameter);
        return suplist.get(0);
    }

    public Page<Customer> cusPageQueryAll( int pageSize,int pageNumber) {
        return adminDao.pageQuery(pageSize,pageNumber);
    }

    public Page<Bigclass> bigPageQueryAll(int pageSize, int pageNumber) {
        return adminDao.pagebigQuery(pageSize,pageNumber);
    }

    public Page<Announcement> annPageQueryAll(int pageSize, int pageNumber) {
        return adminDao.pageannQuery(pageSize,pageNumber);
    }

    public Page<Seller> sePageQueryAll(int pageSize, int pageNumber) {
        return adminDao.pageseQuery(pageSize,pageNumber);
    }

    public Page<ExtGoods> extgPageQueryAll(int pageSize, int pageNumber) {
        return adminDao.pagegoodsQuery(pageSize,pageNumber);
    }

    public Page<ExtSmallclass> extsPageQueryAll(int pageSize, int pageNumber) {
        return adminDao.pagesmQuery(pageSize,pageNumber);
    }

    public Page<Customer> cusPageunionQuery(int pageSize,int pageNumber, String Id, String userName, String userSex) {
        int IdSize = Id.length();
        int userNameSize = userName.length();
        int userSexSize = userSex.length();
        if(IdSize == 0 && userNameSize == 0 && userSexSize ==0 ){

            String sql1 = "select count(1) from customer";
            String sql2 = "select * from customer";
            return adminDao.nuionpageQuery(sql1,sql2,pageSize,pageNumber,null);


        }else if(IdSize > 0 && userNameSize == 0 && userSexSize ==0 ){
            //根据id具体查询
            Object []parameter = {Id};
            String sql1="select count(1) from customer where id = ?";
            String sql2 = "select * from customer where id = ?";
            return adminDao.nuionpageQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize == 0 && userNameSize > 0 && userSexSize ==0){
            //根据姓名模糊查询
            Object []parameter = {"%"+userName+"%"};
            String sql1 = "select count(1) from customer where cusName like ?";
            String sql2 = "select * from customer where cusName like ?";
            return adminDao.nuionpageQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize == 0 && userNameSize == 0 && userSexSize > 0){
            //根据性别查询
            Object []parameter = {userSex};
            String sql1 = "select count(1) from customer where cusSex = ?";
            String sql2 = "select * from customer where cusSex = ?";
            return adminDao.nuionpageQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize > 0 && userNameSize > 0 && userSexSize > 0){
            //根据 姓名 学号 性别 级联查询
            Object []parameter = {Id,"%"+userName+"%",userSex};
            String sql1 = "select count(1) from customer where id = ? and cusName like ? and cusSex = ?";
            String sql2 = "select * from customer where id = ? and cusName like ? and cusSex = ?";
            return adminDao.nuionpageQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize > 0 && userNameSize > 0 && userSexSize == 0){
            //根据 姓名 学号  级联查询
            Object []parameter = {Id,"%"+userName+"%"};
            String sql1 = "select count(1) from customer where id = ? and cusName like ?";
            String sql2 = "select * from customer where id = ? and cusName like ?";
            return adminDao.nuionpageQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize == 0 && userNameSize > 0 && userSexSize > 0){
            //根据  姓名 性别 级联查询
            Object []parameter = {"%"+userName+"%",userSex};
            String sql1 = "select count(1) from customer where  cusName like ? and cusSex = ?";
            String sql2 = "select * from customer where  cusName like ? and cusSex = ?";
            return adminDao.nuionpageQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize > 0 && userNameSize == 0 && userSexSize > 0){
            //根据  学号 性别 级联查询
            Object [] parameter = {Id,userSex};
            String sql1 = "select count(1) from customer where id = ? and cusSex = ?";
            String sql2 = "select * from customer where id = ? and cusSex = ?";
            return adminDao.nuionpageQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }
        return null;
    }

    public Page<ExtGoods> godsPageunionQuery(int pageSize, int pageNumber, String Id, String goodsName, String sellername) {
        int IdSize = Id.length();
        int goodsNameeSize = goodsName.length();
        int sellernameSize = sellername.length();
        if(IdSize == 0 && goodsNameeSize == 0 && sellernameSize ==0 ){

            String sql1 = "select count(1) from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id ";
            String sql2 = "select g.id,g.goodsName,sm.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id";

            return adminDao.nuionpagegoodsQuery(sql1,sql2,pageSize,pageNumber,null);


        }else if(IdSize > 0 && goodsNameeSize == 0 && sellernameSize ==0 ){
            //根据id具体查询
            Object []parameter = {Id};
            String sql1 = "select count(1) from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and g.id=?";
            String sql2 = "select g.id,g.goodsName,sm.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and g.id=?";
            return adminDao.nuionpagegoodsQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize == 0 && goodsNameeSize > 0 && sellernameSize ==0){
            //根据姓名模糊查询
            Object []parameter = {"%"+goodsName+"%"};
            String sql1 = "select count(1) from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and goodsName LIKE ?";
            String sql2 = "select g.id,g.goodsName,sm.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and goodsName LIKE ?";

            return adminDao.nuionpagegoodsQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize == 0 && goodsNameeSize == 0 && sellernameSize > 0){
            //根据性别查询
            Object []parameter = {sellername};
            String sql1 = "select count(1) from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and sellerName=?";
            String sql2 = "select g.id,g.goodsName,sm.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and sellerName=?";

            return adminDao.nuionpagegoodsQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize > 0 && goodsNameeSize > 0 && sellernameSize > 0){
            //根据 姓名 学号 性别 级联查询
            Object []parameter = {Id,"%"+goodsName+"%",sellername};
            String sql1 = "select count(1) from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and g.id=? and goodsName LIKE ? and sellerName=?";
            String sql2 = "select g.id,g.goodsName,sm.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and g.id=? and goodsName LIKE ? and sellerName=?";
            return adminDao.nuionpagegoodsQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize > 0 && goodsNameeSize > 0 && sellernameSize == 0){
            //根据 姓名 学号  级联查询
            Object []parameter = {Id,"%"+goodsName+"%"};
            String sql1 = "select count(1) from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and g.id=? and goodsName LIKE ?";
            String sql2 = "select g.id,g.goodsName,sm.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and g.id=? and goodsName LIKE ?";
            return adminDao.nuionpagegoodsQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize == 0 && goodsNameeSize > 0 && sellernameSize > 0){
            //根据  姓名 性别 级联查询
            Object []parameter = {"%"+goodsName+"%",sellername};
            String sql1 = "select count(1) from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and goodsName LIKE ? and sellerName=?";
            String sql2 = "select g.id,g.goodsName,sm.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and and goodsName LIKE ? and sellerName=?";
            return adminDao.nuionpagegoodsQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize > 0 && goodsNameeSize == 0 && sellernameSize > 0){
            //根据  学号 性别 级联查询
            Object [] parameter = {Id,sellername};
            String sql1 = "select count(1) from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and g.id=? and sellerName=?";
            String sql2 = "select g.id,g.goodsName,sm.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and g.id=? and sellerName=?";
            return adminDao.nuionpagegoodsQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }
        return null;
    }

    public Page<ExtSmallclass> smPageunionQuery(int pageSize, int pageNumber, String Id, String smName, String bigname) {
        int IdSize = Id.length();
        int smNameSize = smName.length();
        int bignameSize = bigname.length();
        if(IdSize == 0 && smNameSize == 0 && bignameSize ==0 ){

            String sql1 = "select count(1) from smallclass sm,bigclass b where sm.smallBigId=b.id ";
            String sql2 = "select sm.id,sm.smallName,sm.smallBigId,sm.smallText,b.bigName from smallclass sm,bigclass b where sm.smallBigId=b.id";

            return adminDao.nuionpagesmQuery(sql1,sql2,pageSize,pageNumber,null);


        }else if(IdSize > 0 && smNameSize == 0 && bignameSize ==0 ){
            //根据id具体查询
            Object []parameter = {Id};
            String sql1 = "select count(1) from smallclass sm,bigclass b where sm.smallBigId=b.id and sm.id=? ";
            String sql2 = "select sm.id,sm.smallName,sm.smallBigId,sm.smallText,b.bigName from smallclass sm,bigclass b where sm.smallBigId=b.id and sm.id=? ";
            return adminDao.nuionpagesmQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize == 0 && smNameSize > 0 && bignameSize ==0){
            //根据姓名模糊查询
            Object []parameter = {"%"+smName+"%"};
            String sql1 = "select count(1) from smallclass sm,bigclass b where sm.smallBigId=b.id and sm.smallName LIKE ? ";
            String sql2 = "select sm.id,sm.smallName,sm.smallBigId,sm.smallText,b.bigName from smallclass sm,bigclass b where sm.smallBigId=b.id and sm.smallName LIKE ?";

            return adminDao.nuionpagesmQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize == 0 && smNameSize == 0 && bignameSize > 0){
            //根据性别查询
            Object []parameter = {bigname};
            String sql1 = "select count(1) from smallclass sm,bigclass b where sm.smallBigId=b.id and b.bigName=?";
            String sql2 = "select sm.id,sm.smallName,sm.smallBigId,sm.smallText,b.bigName from smallclass sm,bigclass b where sm.smallBigId=b.id and b.bigName=?";

            return adminDao.nuionpagesmQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize > 0 && smNameSize > 0 && bignameSize > 0){
            //根据 姓名 学号 性别 级联查询
            Object []parameter = {Id,"%"+smName+"%",bigname};
            String sql1 = "select count(1) from smallclass sm,bigclass b where sm.smallBigId=b.id and sm.id=? and sm.smallName LIKE ? and b.bigName=?";
            String sql2 = "select sm.id,sm.smallName,sm.smallBigId,sm.smallText,b.bigName from smallclass sm,bigclass b where sm.smallBigId=b.id and sm.id=? and sm.smallName LIKE ? and b.bigName=?";
            return adminDao.nuionpagesmQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize > 0 && smNameSize > 0 && bignameSize == 0){
            //根据 姓名 学号  级联查询
            Object []parameter = {Id,"%"+smName+"%"};
            String sql1 = "select count(1) from smallclass sm,bigclass b where sm.smallBigId=b.id and sm.id=? and sm.smallName LIKE ? ";
            String sql2 = "select sm.id,sm.smallName,sm.smallBigId,sm.smallText,b.bigName from smallclass sm,bigclass b where sm.smallBigId=b.id and sm.id=? and sm.smallName LIKE ? ";
            return adminDao.nuionpagesmQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize == 0 && smNameSize > 0 && bignameSize > 0){
            //根据  姓名 性别 级联查询
            Object []parameter = {"%"+smName+"%",bigname};
            String sql1 = "select count(1) from smallclass sm,bigclass b where sm.smallBigId=b.id and sm.smallName LIKE ? and b.bigName=?";
            String sql2 = "select sm.id,sm.smallName,sm.smallBigId,sm.smallText,b.bigName from smallclass sm,bigclass b where sm.smallBigId=b.id and sm.smallName LIKE ? and b.bigName=?";
            return adminDao.nuionpagesmQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }else if(IdSize > 0 && smNameSize == 0 && bignameSize > 0){
            //根据  学号 性别 级联查询
            Object [] parameter = {Id,bigname};
            String sql1 = "select count(1) from smallclass sm,bigclass b where sm.smallBigId=b.id and sm.id=? and b.bigName=?";
            String sql2 = "select sm.id,sm.smallName,sm.smallBigId,sm.smallText,b.bigName from smallclass sm,bigclass b where sm.smallBigId=b.id and sm.id=? and b.bigName=?";
            return adminDao.nuionpagesmQuery(sql1,sql2,pageSize,pageNumber,parameter);
        }
        return null;
    }

    public Boolean delcus(int id) {
        return adminDao.delcus(id);
    }

    public Boolean delbig(int id) {
        return adminDao.delbig(id);
    }

    public Boolean delann(int id) {
        return adminDao.delann(id);
    }

    public Boolean delgods(int id) {
        String sql="delete from goods where id=?";
        Object[] parameter={id};
        int num=adminDao.update(sql,parameter);
        if (num>0){
            return true;
        }
        return false;
    }

    public Boolean delse(int id) {
        String sql="delete from seller where id=?";
        Object [] parameter={id};
        int num=adminDao.update(sql,parameter);
        if (num>0) {
            return true;
        }
        return false;
    }

    public Boolean delsm(int id) {
        String sql="delete from smallclass where id=? and id not in(select goodsSmalId from goods GROUP BY goodsSmalId)";
        Object [] parameter={id};
        int num=adminDao.update(sql,parameter);
        if (num>0) {
            return true;
        }
        return false;
    }

    public Superuser QueryByid(int id) {
        String sql="select * from superuser where id=?";
        Object [] parameter={id};
        List<Superuser> suplist=adminDao.query(sql,parameter);
        if (suplist!=null&&suplist.size()>0) {
            return suplist.get(0);
        }
        return null;
    }

    public Seller QueryByseid(int id) {
        String sql="select * from seller where id=?";
        Object [] parameter={id};
        List<Seller> selist=adminDao.sequery(sql,parameter);
        if (selist!=null&&selist.size()>0) {
            return selist.get(0);
        }
        return null;
    }

    public Customer cusQueryByid(int id) {
        String sql="select * from customer where id=?";
        Object [] parameter={id};
        List<Customer> cuslist=adminDao.cusquery(sql,parameter);
        if (cuslist!=null&&cuslist.size()>0) {
            return cuslist.get(0);
        }
        return null;
    }

    public Bigclass bigQueryByid(int id) {
        String sql="select * from bigclass where id=?";
        Object [] parameter={id};
        List<Bigclass> biglist=adminDao.bigquery(sql,parameter);
        if (biglist!=null&&biglist.size()>0){
            return biglist.get(0);
        }
        return null;
    }

    public Announcement annQueryByid(int id) {
        String sql="select *from announcement where id=?";
        Object [] parameter={id};
        List<Announcement> annlist=adminDao.annquery(sql,parameter);
        if (annlist!=null&&annlist.size()>0) {
            return annlist.get(0);
        }
        return null;
    }

    public ExtGoods extgQueryByid(int id) {
        String sql="select g.id,g.goodsName,sm.smallName,g.goodsSmalId,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,g.goodsType,g.goodsSeId,g.goodsDiscId,se.sellerName,d.discRate from goods g,seller se,smallclass sm,discount d where g.goodsSmalId=sm.id and g.goodsSeId=se.id and g.goodsDiscId=d.id and g.id=?";
        Object[] parameter={id};
        List<ExtGoods> extglist=adminDao.queryextg(sql,parameter);
        if (extglist!=null&&extglist.size()>0) {
            return extglist.get(0);
        }
        return null;
    }

    public ExtSmallclass extsmByid(int id) {
        String sql="select sm.id,sm.smallName,sm.smallBigId,sm.smallText,b.bigName from smallclass sm,bigclass b where sm.smallBigId=b.id and sm.id=?";
        Object [] parameter={id};
        List<ExtSmallclass> smlist=adminDao.smquery(sql,parameter);
        if (smlist!=null&&smlist.size()>0) {
            return smlist.get(0);
        }
        return null;
    }

    public List<Seller> showseller() {
        String sql="select * from seller";
        return adminDao.sequery(sql);
    }

    public List<Discount> showdiscount() {
        String sql="select * from discount";
        return adminDao.disquery(sql);
    }

    public List<Smallclass> showsmclass() {
        String sql="select * from smallclass";
        return adminDao.smquery(sql);
    }

    public List<Bigclass> showbigclass() {
        String sql="select * from bigclass";
        return adminDao.bigquery(sql);
    }

    public int addbig(Bigclass big) {
        String sql="INSERT into bigclass (bigName,bigText) VALUES(?,?)";
        Object [] parameter={big.getBigName(),big.getBigText()};
        return adminDao.update(sql,parameter);
    }

    public int addann(Announcement ann) {
        String sql="INSERT into announcement (anTitle,anText,anDate) VALUES(?,?,?)";
        Object [] parameter={ann.getAnTitle(),ann.getAnText(),ann.getAnDate()};
        return adminDao.update(sql,parameter);
    }

    public int addgods(Goods gods) {
        String sql="insert into goods (goodsName,goodsSmalId,goodsMoney,goodsNumber,goodsCarriage,goodsType,goodsSeId,goodsDiscId) VALUES(?,?,?,?,?,?,?,?)";
        Object [] parameter={gods.getGoodsName(),gods.getGoodsSmalId(),gods.getGoodsMoney(),gods.getGoodsNumber(),gods.getGoodsCarriage(),gods.getGoodsType(),gods.getGoodsSeId(),gods.getGoodsDiscId()};
        return adminDao.update(sql,parameter);
    }

    public int addse(Seller se) {
        String sql="insert into seller (sellerName,sellerUser,sellerPassword,sellerSex,sellerBirthday,sellerIdCard,sellerEmail,sellerTel,sellerAddress) VALUES(?,?,?,?,?,?,?,?,?)";
        Object[] parameter={se.getSellerName(),se.getSellerUser(),se.getSellerPassword(),se.getSellerSex(),se.getSellerBirthday(),se.getSellerIdCard(),se.getSellerEmail(),se.getSellerTel(),se.getSellerAddress()};
        return adminDao.update(sql,parameter);
    }

    public int addsm(Smallclass sm) {
        String sql="insert into smallclass (smallName,smallBigId,smallText) values(?,?,?)";
        Object [] parameter={sm.getSmallName(),sm.getSmallBigId(),sm.getSmallText()};
        return adminDao.update(sql,parameter);
    }

    public int pdgods(String godsname, int sellerid) {
        String sql="select * from goods where goodsName=? and goodsSeId=?";
        Object [] parameter={godsname,sellerid};
        return adminDao.pdgoods(sql,parameter);
    }

    public int supUpdate(Superuser sup) {
        String sql="UPDATE superuser SET userStatus=?,userID=?,userLoginName=? where id=?";
        Object [] parameter={sup.getUserStatus(),sup.getUserId(),sup.getUserLoginName(),sup.getId()};
        return adminDao.update(sql,parameter);
    }

    public int cusUpdate(Customer cus) {
        String sql="update customer set cusLoginName=?,cusEmail=?,cusSex=?,cusHobby=?,cusCode=?,cusBirthday=? where id=?";
        Object[] parameter={cus.getCusLoginName(),cus.getCusEmail(),cus.getCusSex(),cus.getCusHobby(),cus.getCusCode(),cus.getCusBirthday(),cus.getId()};

        return adminDao.update(sql,parameter);
    }

    public int bigUpdate(Bigclass big) {
        String sql="UPDATE bigclass set bigName=?,bigText=? where id=?";
        Object [] parameter={big.getBigName(),big.getBigText(),big.getId()};
        return adminDao.update(sql,parameter);
    }

    public int annUpdate(Announcement ann) {
        String sql="UPDATE announcement SET anTitle=?,anText=?,anDate=? where id=?";
        Object [] parameter={ann.getAnTitle(),ann.getAnText(),ann.getAnDate(),ann.getId()};
        return adminDao.update(sql,parameter);
    }

    public int godsUpdate(Goods gods) {
        String sql="UPDATE goods set goodsName=?,goodsSmalId=?,goodsMoney=?,goodsNumber=?,goodsCarriage=?,goodsType=?,goodsSeId=?,goodsDiscId=? where id=?";
        Object [] parameter={gods.getGoodsName(),gods.getGoodsSmalId(),gods.getGoodsMoney(),gods.getGoodsNumber(),gods.getGoodsCarriage(),gods.getGoodsType(),gods.getGoodsSeId(),gods.getGoodsDiscId(),gods.getId()};
        return adminDao.update(sql,parameter);
    }

    public int seUpdate(Seller se) {
        String sql="UPDATE seller set sellerName=?,sellerUser=?,sellerPassword=?,sellerSex=?,sellerBirthday=?,sellerIdCard=?,sellerEmail=?,sellerTel=?,sellerAddress=? where id=?";
        Object [] parameter={se.getSellerName(),se.getSellerUser(),se.getSellerPassword(),se.getSellerSex(),se.getSellerBirthday(),se.getSellerIdCard(),se.getSellerEmail(),se.getSellerTel(),se.getSellerAddress(),se.getId()};
        return adminDao.update(sql,parameter);
    }

    public int smUpdate(Smallclass sm) {
        String sql="UPDATE smallclass set smallName=?,smallBigId=?,smallText=? where id=?";
        Object [] parameter={sm.getSmallName(),sm.getSmallBigId(),sm.getSmallText(),sm.getId()};
        return adminDao.update(sql,parameter);
    }


}
