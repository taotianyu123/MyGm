package service;

import pojo.*;
import sun.net.smtp.SmtpClient;

import java.util.List;

public interface AdminServiceDao {
    Superuser suoLogin(String loginname,String pwd);

    Page<Customer> cusPageQueryAll( int pageSize,int pageNumber);

    Page<Bigclass> bigPageQueryAll(int pageSize, int pageNumber);

    Page<Announcement> annPageQueryAll(int pageSize, int pageNumber);

    Page<Seller> sePageQueryAll(int pageSize, int pageNumber);

    Page<ExtGoods> extgPageQueryAll(int pageSize, int pageNumber);

    Page<ExtSmallclass> extsPageQueryAll(int pageSize, int pageNumber);

    Page<Customer> cusPageunionQuery(int pageSize,int pageNumber,String Id, String userName, String userSex);

    Page<ExtGoods> godsPageunionQuery(int pageSize,int pageNumber,String Id, String goodsName, String sellername);

    Page<ExtSmallclass> smPageunionQuery(int pageSize,int pageNumber,String Id, String smName, String bigname);

    Boolean delcus(int id);

    Boolean delbig(int id);

    Boolean delann(int id);

    Boolean delgods(int id);

    Boolean delse(int id);

    Boolean delsm(int id);

    Superuser QueryByid(int id);

    Seller QueryByseid(int id);

    int supUpdate(Superuser sup);

    int cusUpdate(Customer cus);

    int bigUpdate(Bigclass big);

    int annUpdate(Announcement ann);

    int godsUpdate(Goods gods);

    int seUpdate(Seller se);

    int smUpdate(Smallclass sm);

    Customer cusQueryByid(int id);

    Bigclass bigQueryByid(int id);

    Announcement annQueryByid(int id);

    ExtGoods extgQueryByid(int id);

    ExtSmallclass extsmByid(int id);

    List<Seller> showseller();

    List<Discount> showdiscount();

    List<Smallclass> showsmclass();

    List<Bigclass> showbigclass();

    int addbig(Bigclass big);

    int addann(Announcement ann);

    int addgods(Goods gods);

    int addse(Seller se);

    int addsm(Smallclass sm);

    int pdgods(String godsname,int sellerid);
}
