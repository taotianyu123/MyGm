package controller;

import com.alibaba.fastjson.JSONArray;
import pojo.*;
import service.impl.AdminServiceDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "AdminServlet",urlPatterns = "/doadmin")
public class AdminServlet extends HttpServlet {
    String name;
    String id;
    String sex;
    String goodsid;
    String goodsname;
    String sellername;
    String smid;
    String smname;
    String bigname;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //编码方式统一
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取输入了对象
        PrintWriter out = response.getWriter();
        //获取path路径
        String path = request.getContextPath();
        //获取session对象
        HttpSession session = request.getSession();
        //获取参数action的值
        //学生登录
        String action = request.getParameter("action");
        //获取学生服务对象
        AdminServiceDaoImpl adminDao = AdminServiceDaoImpl.getInstance();
        /**
         * 登录
         */
        if (action != null && action.equals("login")) {
                String loginname=request.getParameter("userName");
                String pwd=request.getParameter("userPassword");
                Superuser sup=adminDao.suoLogin(loginname,pwd);
                if (sup!=null){
                    session.setAttribute("Superuser",sup);
                    response.sendRedirect(path + "/page/index.jsp");
                } else {
                    response.sendRedirect("gmLogin.html?msg=0");
                }
            }
    /**
     * 查询所有
     */
    if (action!=null&&action.equals("queryall")){
        int pageSIze = 5;
        int pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
        Page<Customer> page = adminDao.cusPageQueryAll( pageSIze,pageNumber);
        session.setAttribute("Page", page);
        request.setAttribute("ServletUrl", "/doadmin?action=queryall");
        request.getRequestDispatcher(path + "/page/showCoustomer.jsp").forward(request, response);
    }

        /**
         * 条件查询
         */
        if (action!=null&&action.equals("unionQuery")){
            int pageSIze = 5;
            int pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
            if (request.getParameter("Id")!=null){
                id=request.getParameter("Id");
            }
            if (request.getParameter("userName")!=null){
                name=request.getParameter("userName");
            }
            if (request.getParameter("userSex")!=null){
                sex=request.getParameter("userSex");
            }
            if (name.length()==0&&id.length()==0&&sex.length()==0){

                Page<Customer> page = adminDao.cusPageQueryAll( pageSIze,pageNumber);
                session.setAttribute("Page", page);
                request.setAttribute("ServletUrl", "/doadmin?action=queryall");
                request.getRequestDispatcher(path + "/page/showCoustomer.jsp").forward(request, response);
            }else{
                Page<Customer> page=adminDao.cusPageunionQuery(pageSIze,pageNumber,id,name,sex);
                session.setAttribute("Page",page);
                request.setAttribute("ServletUrl","/doadmin?action=unionQuery");
                request.getRequestDispatcher(path+"/page/showCoustomer.jsp").forward(request,response);
            }
        }

        /**
         * 删除用户
         */
        if (action!=null&&action.equals("delById")){
            int id=Integer.valueOf(request.getParameter("cusid"));
            boolean flag=adminDao.delcus(id);
            if (flag){
                out.print("删除成功!");
            }else{
                out.print("删除失败!");
            }
        }
        /**
         * 修改管理员
         */
        if (action!=null&&action.equals("queryById")){
            int id=Integer.valueOf(request.getParameter("Id"));
            Superuser sup=adminDao.QueryByid(id);
            if (sup!=null){
                session.setAttribute("Superuser",sup);
                response.sendRedirect(path+"/page/updsuponInfo.jsp");
            }
        }
        if (action!=null&&action.equals("updateById")){
        int id=Integer.valueOf(request.getParameter("Id"));
        int status=Integer.valueOf(request.getParameter("userStatus"));
        String userid=request.getParameter("userid");
        String name=request.getParameter("userName");
        String userloginname=request.getParameter("userLoginName");
        Superuser sup=new Superuser(id,name,null,null,status,userid,userloginname);
        int i=adminDao.supUpdate(sup);
        if (i>0){
            session.setAttribute("Superuser",sup);
            response.sendRedirect(path + "/page/otherPage.jsp?msg=1");
        }else{
            out.print("<h3>修改失败!</h3>");
        }
        }
        /**
         * 修改用户
         */
        if (action!=null&&action.equals("queryBycusId")){

            int cusid=Integer.valueOf(request.getParameter("cusid"));
            Customer cus=adminDao.cusQueryByid(cusid);
            if (cus!=null){
                session.setAttribute("Customer",cus);
                response.sendRedirect(path+"/page/updcusonInfo.jsp");
            }
        }
        if (action!=null&&action.equals("updateBycusId")){
            int id=Integer.valueOf(request.getParameter("Id"));
            String name=request.getParameter("cusName");
            String loginname=request.getParameter("cusloginname");
            String email=request.getParameter("cusemail");
            String cusSex=request.getParameter("cusSex");
            String cushobby=request.getParameter("cushobby");
            String cuscode=request.getParameter("cuscode");
            Date cusbirthday=Date.valueOf(request.getParameter("cusbirthday"));
            Customer cus=new Customer(id,name,loginname,null,email,cusSex,null,cushobby,cuscode,cusbirthday);
            int num=adminDao.cusUpdate(cus);
            if (num>0){
                out.print("修改成功!");
            }else{
                out.print("修改失败!");
            }

        }

        /**
         * 查询大分类
         */
        if (action!=null&&action.equals("queryallbig")){
            int pageSIze = 5;
            int pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
            Page<Bigclass> page=adminDao.bigPageQueryAll(pageSIze,pageNumber);
            session.setAttribute("Page", page);
            request.setAttribute("ServletUrl", "/doadmin?action=queryallbig");
            request.getRequestDispatcher(path + "/page/showBigInfo.jsp").forward(request, response);
        }

        /**
         * 删除大分类
         */
            if (action!=null&&action.equals("delBybigId")){
                int id=Integer.valueOf(request.getParameter("bigId"));
                boolean flag=adminDao.delbig(id);
                if (flag){
                    out.print("删除成功!");
                }else{
                    out.print("删除失败!");
                }
            }

        /**
         * 查询要修改的大分类信息,以及修改
         */
        if (action!=null&&action.equals("queryBybigId")){
            int id=Integer.valueOf(request.getParameter("bigId"));
            Bigclass big=adminDao.bigQueryByid(id);
            if (big!=null) {
                session.setAttribute("Bigclass", big);
                response.sendRedirect(path + "/page/updbigonInfo.jsp");
            }
        }
        if (action!=null&&action.equals("updateBybigId")){
            int id=Integer.valueOf(request.getParameter("Id"));
            String name=request.getParameter("bigName");
            String text=request.getParameter("bigText");
            Bigclass big=new Bigclass(id,name,text);
            int num=adminDao.bigUpdate(big);
            if (num>0){
                out.print("修改成功!");
            }else{
                out.print("修改失败!");
            }
        }

        /**
         * 添加大分类
         */
        if (action!=null&&action.equals("addbig")){
            String name=request.getParameter("bigname");
            String text=request.getParameter("bigtext");
            int id=0;
            Bigclass big=new Bigclass(id,name,text);
            int num=adminDao.addbig(big);
            if (num>0){
                out.print("添加成功!");
            }else{
                out.print("修改失败!");
            }
        }

        /**
         * 查询小分类
         */
        if (action!=null&&action.equals("querysm")){
                int pageSIze = 5;
                int pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
                Page<ExtSmallclass> page=adminDao.extsPageQueryAll(pageSIze,pageNumber);
                session.setAttribute("Page", page);
                request.setAttribute("ServletUrl", "/doadmin?action=querysm");
                request.getRequestDispatcher(path + "/page/showsmInfo.jsp").forward(request, response);
        }
        /**
         * 级联查询小分类
         */
        if (action!=null&&action.equals("unionsmQuery")){
            int pagesize=5;
            int pagenumber=Integer.valueOf(request.getParameter("pageNumber"));
            if (request.getParameter("Id")!=null) {
               smid = request.getParameter("Id");
            }
            if (request.getParameter("smName")!=null) {
                 smname = request.getParameter("smName");
            }
            if (request.getParameter("bigname")!=null) {
                bigname = request.getParameter("bigname");
            }
            if (smid.length()==0&&smname.length()==0&&bigname.length()==0){
                Page<ExtSmallclass> page=adminDao.extsPageQueryAll(pagesize,pagenumber);
                session.setAttribute("Page", page);
                request.setAttribute("ServletUrl", "/doadmin?action=querysm");
                request.getRequestDispatcher(path + "/page/showsmInfo.jsp").forward(request, response);
            }else{
                Page<ExtSmallclass> page=adminDao.smPageunionQuery(pagesize,pagenumber,smid,smname,bigname);
                session.setAttribute("Page",page);
                request.setAttribute("ServletUrl","/doadmin?action=unionsmQuery");
                request.getRequestDispatcher(path+"/page/showsmInfo.jsp").forward(request,response);
            }

        }
        /**
         * 删除小分类
         */
        if (action!=null&&action.equals("delBysmId")){
            int id=Integer.valueOf(request.getParameter("smId"));
            boolean flag=adminDao.delsm(id);
            if (flag){
                out.print("删除成功!");
            }else{
                out.print("删除失败!");
            }
        }

        /**
         * 根据id查询相关的数据
         */
        if (action!=null&&action.equals("queryBysmId")){
            int id=Integer.valueOf(request.getParameter("smId"));
            ExtSmallclass smlist=adminDao.extsmByid(id);
            session.setAttribute("ExtSmallclass",smlist);
            response.sendRedirect(path+"/page/updsmInfo.jsp");
        }
        /**
         * 修改小分类
         */
        if(action!=null&&action.equals("updateBysmId")){
            int id=Integer.valueOf(request.getParameter("Id"));
            String smname=request.getParameter("smName");
            int bigid=Integer.valueOf(request.getParameter("bigname"));
            String smtext=request.getParameter("smText");
            Smallclass sm=new Smallclass(id,smname,bigid,smtext);
            int num=adminDao.smUpdate(sm);
            if (num>0){
                out.print("修改成功!");
            }else{
                out.print("修改失败!");
            }
        }
        /**
         * 添加小分类
         */
        if (action!=null&&action.equals("addsm")){
            int id=0;
            String smname=request.getParameter("smname");
            int bigid=Integer.valueOf(request.getParameter("bigname"));
            String smtext=request.getParameter("smtext");
            Smallclass sm=new Smallclass(id,smname,bigid,smtext);
            int num=adminDao.addsm(sm);
            if (num>0){
                out.print("添加成功!");
            }else{
                out.print("添加失败!");
            }
        }
        /**
         * 通告
         */
        if (action!=null&&action.equals("queryannall")){
            int pageSIze = 5;
            int pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
            Page<Announcement> page = adminDao.annPageQueryAll( pageSIze,pageNumber);
            session.setAttribute("Page", page);
            request.setAttribute("ServletUrl", "/doadmin?action=queryannall");
            request.getRequestDispatcher(path + "/page/showAnnInfo.jsp").forward(request, response);
        }

        /**
         * 删除通告
         */
        if (action!=null&&action.equals("delByannId")){
            int id=Integer.valueOf(request.getParameter("annId"));
            boolean flag=adminDao.delann(id);
            if (flag){
                out.print("删除成功!");
            }else{
                out.print("删除失败!");
            }
        }

        /**
         * 查询要修改的公告和修改
         */
        if (action!=null&&action.equals("queryByannId")){
            int id=Integer.valueOf(request.getParameter("annId"));
            Announcement ann=adminDao.annQueryByid(id);
            session.setAttribute("Announcement",ann);
            response.sendRedirect(path+"/page/updannonInfo.jsp");
        }
        if (action!=null&&action.equals("updateByannId")){
            int id=Integer.valueOf(request.getParameter("Id"));
            String title=request.getParameter("annTitle");
            String text=request.getParameter("annText");
            Date date=new Date(System.currentTimeMillis());
            Announcement ann=new Announcement(id,title,text,date);
            int num=adminDao.annUpdate(ann);
            if (num>0){
                out.print("修改成功!");
            }else{
                out.print("修改失败!");
            }
        }

        /**
         * 添加公告
         */
        if (action!=null&&action.equals("addann")){
            String title=request.getParameter("annTitle");
            String text=request.getParameter("anntext");
            Date date=new Date(System.currentTimeMillis());
            int id=0;
            Announcement ann=new Announcement(id,title,text,date);
            int num=adminDao.addann(ann);
            if (num>0){
                out.print("添加成功!");
            }else{
                out.print("添加失败!");
            }
        }

        /**
         * 查询商品信息
         */
        if (action!=null&&action.equals("querygoodsall")){
            int pageSIze = 5;
            int pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
            Page<ExtGoods> page=adminDao.extgPageQueryAll(pageSIze,pageNumber);
            session.setAttribute("Page", page);
            request.setAttribute("ServletUrl", "/doadmin?action=querygoodsall");
            request.getRequestDispatcher(path + "/page/showGoods.jsp").forward(request, response);
        }

        /**
         * 商品条件查询
         */
        if (action!=null&&action.equals("uniongoodsQuery")){
            int pageSIze = 5;
            int pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
            if (request.getParameter("Id")!=null){
                goodsid=request.getParameter("Id");
            }
            if (request.getParameter("goodsName")!=null){
                goodsname=request.getParameter("goodsName");
            }
            if (request.getParameter("sellerName")!=null){
                sellername=request.getParameter("sellerName");
            }
            if (goodsname.length()==0&&goodsid.length()==0&&sellername.length()==0){
                Page<ExtGoods> page = adminDao.extgPageQueryAll( pageSIze,pageNumber);
                session.setAttribute("Page", page);
                request.setAttribute("ServletUrl", "/doadmin?action=querygoodsall");
                request.getRequestDispatcher(path + "/page/showGoods.jsp").forward(request, response);
            }else{
                Page<ExtGoods> page=adminDao.godsPageunionQuery(pageSIze,pageNumber,goodsid,goodsname,sellername);
                session.setAttribute("Page",page);
                request.setAttribute("ServletUrl","/doadmin?action=uniongoodsQuery");
                request.getRequestDispatcher(path+"/page/showGoods.jsp").forward(request,response);
            }
        }
        /**
         * 根据商品id删除
         */
        if (action!=null&&action.equals("delBygoodsId")){
        int id=Integer.valueOf(request.getParameter("goodsid"));
        boolean flag=adminDao.delgods(id);
        if (flag){
            out.print("删除成功!");
        }else{
            System.out.println("删除失败!");
        }
        }
        /**
         * 根据商品id查询具体数据
         */

          if (action!=null&&action.equals("queryBygoodsId")){
            int id=Integer.valueOf(request.getParameter("goodsid"));
            ExtGoods extg=adminDao.extgQueryByid(id);
            session.setAttribute("ExtGoods",extg);
            response.sendRedirect(path+"/page/updgoodsInfo.jsp");
        }
        /**
         * ajax做的下拉列表
         */


        if (action!=null&& action.equals("showdiscount")){
            List<Discount> dislist=adminDao.showdiscount();
            System.out.println(JSONArray.toJSONString(dislist));
            out.print(JSONArray.toJSONString(dislist));
        }

        if (action!=null&& action.equals("showbigclass")){
            List<Bigclass> biglist=adminDao.showbigclass();
            System.out.println(JSONArray.toJSONString(biglist));
            out.print(JSONArray.toJSONString(biglist));
        }

        if (action!=null&& action.equals("showsellname")){
            List<Seller> selist=adminDao.showseller();
            System.out.println(JSONArray.toJSONString(selist));
            out.print(JSONArray.toJSONString(selist));
        }

        if (action!=null&& action.equals("showsmclass")){
            List<Smallclass> smlist=adminDao.showsmclass();
            System.out.println(JSONArray.toJSONString(smlist));
            out.print(JSONArray.toJSONString(smlist));
        }


        /**
         * 修改商品信息
         */
        if (action!=null&&action.equals("updateBygodsId")){
            int id=Integer.valueOf(request.getParameter("Id"));
            String name=request.getParameter("godsName");
            int smname=Integer.valueOf(request.getParameter("smclassName"));
            double money=Double.valueOf(request.getParameter("goodsMoney"));
            int num=Integer.valueOf(request.getParameter("goodsNumber"));
            double pay=Double.valueOf(request.getParameter("goodsCarriage"));
            int type=Integer.valueOf(request.getParameter("goodsType"));
            int discount=Integer.valueOf(request.getParameter("discRate"));
            int seid=Integer.valueOf(request.getParameter("sellerName"));
            Goods g=new Goods(id,name,smname,money,num,null,pay,type,seid,discount);
            int flag=adminDao.godsUpdate(g);
            if (flag>0){
                out.print("修改成功!");
            }else {
                out.print("修改失败!");
            }
        }
        /**
         * 判断商品是否存在
         */
        if (action!=null&&action.equals("pdname")){
            String godsname=request.getParameter("godname");
            System.out.println(request.getParameter("sellerid"));
            int sellerid=Integer.valueOf(request.getParameter("sellerid"));
            int i=adminDao.pdgods(godsname,sellerid);
            System.out.println(JSONArray.toJSONString(i));
            out.print(JSONArray.toJSONString(i));
        }
        /**
         * 添加商品
         */
        if (action!=null&&action.equals("addgods")){
            int id=0;
            String name=request.getParameter("godsName");
            int smname=Integer.valueOf(request.getParameter("smclassName"));
            double money=Double.valueOf(request.getParameter("goodsMoney"));
            int num=Integer.valueOf(request.getParameter("goodsNumber"));
            double pay=Double.valueOf(request.getParameter("goodsCarriage"));
            int type=Integer.valueOf(request.getParameter("goodsType"));
            int discount=Integer.valueOf(request.getParameter("discRate"));
            int seid=Integer.valueOf(request.getParameter("sellerName"));
            Goods g=new Goods(id,name,smname,money,num,null,pay,type,seid,discount);
            int flag=adminDao.addgods(g);
            if (flag>0){
                out.print("添加成功!");
            }else{
                out.print("添加失败!");
            }
        }

        /**
         * 查询商家
         */
        if(action!=null&&action.equals("queryseller")){
            int pageSIze = 5;
            int pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
            Page<Seller> page=adminDao.sePageQueryAll(pageSIze,pageNumber);
            session.setAttribute("Page", page);
            request.setAttribute("ServletUrl", "/doadmin?action=queryseller");
            request.getRequestDispatcher(path + "/page/showSeller.jsp").forward(request, response);
        }
        /**
         * 删除商家
         */
        if (action!=null&&action.equals("delByseId")){
            int id=Integer.valueOf(request.getParameter("seid"));
            boolean flag=adminDao.delse(id);
            if (flag){
                out.print("删除成功!");
            }else{
                out.print("删除失败!");
            }
        }
        /**
         * 根据id找要修改的商家信息
         */
        if (action!=null&&action.equals("queryByseId")){
            int id=Integer.valueOf(request.getParameter("seid"));
            Seller se=adminDao.QueryByseid(id);
            session.setAttribute("Seller",se);
            response.sendRedirect(path+"/page/updseInfo.jsp");
        }
        /**
         * 修改商家信息
         */
        if (action!=null&&action.equals("updateByseId")){
            int id=Integer.valueOf(request.getParameter("Id"));
            String name=request.getParameter("sellerName");
            String user=request.getParameter("sellerUser");
            String pwd=request.getParameter("sellerPassword");
            String sex=request.getParameter("sellerSex");
            Date birth=Date.valueOf(request.getParameter("sellerBirthday"));
            String card=request.getParameter("sellerIdCard");
            String email=request.getParameter("sellerEmail");
            String tel=request.getParameter("sellerTel");
            String address=request.getParameter("sellerAddress");
            Seller se=new Seller(id,name,user,pwd,sex,birth,card,email,tel,address);
            int num=adminDao.seUpdate(se);
            if (num>0){
                out.print("修改成功!");
            }else{
                out.print("修改失败!");
            }
        }
        /**
         * 添加商家信息
         */
        if (action!=null&&action.equals("addse")){
            int id=0;
            String name=request.getParameter("sellerName");
            String user=request.getParameter("sellerUser");
            String pwd=request.getParameter("sellerPassword");
            String sex=request.getParameter("sellerSex");
            Date birth=Date.valueOf(request.getParameter("sellerBirthday"));
            String card=request.getParameter("sellerIdCard");
            String email=request.getParameter("sellerEmail");
            String tel=request.getParameter("sellerTel");
            String address=request.getParameter("sellerAddress");
            Seller se=new Seller(id,name,user,pwd,sex,birth,card,email,tel,address);
            int num=adminDao.addse(se);
            if (num>0){
                out.print("添加成功!");
            }else{
                out.print("添加失败!");
            }
        }
    }
}

