package controller;

import core.util.UploadFile;
import pojo.ExtGoods;
import pojo.Goods;
import pojo.Page;
import pojo.Seller;
import service.impl.AdminServiceDaoImpl;
import service.impl.sellerServiceDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Map;

@WebServlet(name = "SellerServlet",urlPatterns = "/dose")
public class SellerServlet extends HttpServlet {
    int seid;
    String goodsid;
    String goodsname;
    String smclassname;
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
        sellerServiceDaoImpl sellerdao = sellerServiceDaoImpl.getInstance();
        /**
         * 商家登录
         */
        if (action!=null&&action.equals("login")){
            String loginname=request.getParameter("userName");
            String pwd=request.getParameter("userPassword");
            Seller seller=sellerdao.seLogin(loginname,pwd);
            if (seller!=null){
                session.setAttribute("Seller",seller);
                response.sendRedirect(path + "/sellerPage/seindex.jsp");
            } else {
                response.sendRedirect("gmLogin.html?msg=0");
            }
        }
        /**
         * 修改商家
         */
        if(action!=null&&action.equals("querybyseid")){
            int id=Integer.valueOf(request.getParameter("Id"));
            Seller se=sellerdao.queryseid(id);
            if (se!=null){
                session.setAttribute("Seller",se);
                response.sendRedirect(path+"/sellerPage/updseInfo.jsp");
            }
        }
        if (action!=null&&action.equals("updateById")){
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
            boolean flag=sellerdao.updatese(se);
            if (flag){
                session.setAttribute("Seller",se);
                response.sendRedirect(path+"/sellerPage/seotherPage.jsp?msg=1");
            }else{
                out.print("修改失败!");
            }
        }
        /**
         * 查询商品
         */
        if (action!=null&&action.equals("querygoodsall")){
            int pagesize=5;
            int paranumber=Integer.valueOf(request.getParameter("pageNumber"));
            if (request.getParameter("seid")!=null) {
                seid = Integer.valueOf(request.getParameter("seid"));
            }
            Page<ExtGoods> page=sellerdao.extgPageQueryAll(pagesize,paranumber,seid);
            System.out.println(page);
            session.setAttribute("Id",seid);
            session.setAttribute("Page", page);
            request.setAttribute("ServletUrl", "/dose?action=querygoodsall");
            request.getRequestDispatcher(path + "/sellerPage/seshowGoods.jsp").forward(request, response);
        }
        /**
         * 条件查询商品
         */
        if (action!=null&&action.equals("uniongoodsQuery")){
            if (request.getParameter("seid")!=null) {
                seid = Integer.valueOf(request.getParameter("seid"));
            }
            int pageSIze = 5;
            int pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
            if (request.getParameter("Id")!=null){
                goodsid=request.getParameter("Id");
            }
            if (request.getParameter("goodsName")!=null){
                goodsname=request.getParameter("goodsName");
            }
            if (request.getParameter("smclassName")!=null){
                smclassname=request.getParameter("smclassName");
            }
            if (goodsname.length()==0&&goodsid.length()==0&&smclassname.length()==0){
                Page<ExtGoods> page = sellerdao.extgPageQueryAll( pageSIze,pageNumber,seid);
                session.setAttribute("Page", page);
                request.setAttribute("ServletUrl", "/dose?action=querygoodsall");
                request.getRequestDispatcher(path + "/sellerPage/seshowGoods.jsp").forward(request, response);
            }else{
                Page<ExtGoods> page=sellerdao.godsPageunionQuery(pageSIze,pageNumber,goodsid,goodsname,smclassname,seid);
                session.setAttribute("Page",page);
                request.setAttribute("ServletUrl","/dose?action=uniongoodsQuery");
                request.getRequestDispatcher(path+"/sellerPage/seshowGoods.jsp").forward(request,response);
            }
        }
        /**
         * 添加商品
         */
        if (action!=null&&action.equals("addgo")){
            int id=Integer.valueOf(request.getParameter("seid"));
            session.setAttribute("seid",id);
            response.sendRedirect(path+"/sellerPage/seaddgoods.jsp");
        }
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
            String filePath = getServletContext().getRealPath("GoodsImage");
            Map<String, String> map = UploadFile.uploadUtil(filePath, request, 1024 * 1024 * 10L, "userHobby");
            map.get("userImage");
            ////定义要上传的文件的保存的位置
            //    String filePatu="GoodsImage";
            //    String paths=getServletContext().getRealPath(filePatu);
            //    Map<String,String> map= UploadFile.uploadUtil(paths,request,1024*1024*10,"userHobby");
            //    Goods goods=new Goods();
            //    goods.setId(Integer.valueOf(map.get("id")));
            //    goods.setGoodsName(map.get("goodsName").trim());
            //    goods.setGoodsMoney(Double.valueOf(map.get("goodsMoney")));
            //    goods.setGoodsNumber(Integer.valueOf(map.get("goodsNumber")));
            //   /* goods.setGoodsImage(map.get("goodsImage"));*/
            //    goods.setGoodsCarriage(Double.valueOf(map.get("goodsCarriage")));
            //    goods.setGoodsType(Integer.valueOf(map.get("goodsType")));
            //    goods.setGoodsSmalId(Integer.valueOf(map.get("goodsSmalId")));
            //    goods.setGoodsSeId(Integer.valueOf(map.get("goodsSeId")));
            //    goods.setGoodsDiscId(Integer.valueOf(map.get("goodsDiscId")));
            //    int i=goodsService.goodsUpdate(goods);
            //    if(i>0){
            //        out.print("修改成功");
            //    }else {
            //        out.print("修改失败");
            //    }

            Goods g=new Goods(id,name,smname,money,num,map.get("userImage"),pay,type,seid,discount);
            int flag=sellerdao.addgods(g);
            if (flag>0){
                out.print("添加成功!");
            }else{
                out.print("添加失败!");
            }
        }
        }
    }
