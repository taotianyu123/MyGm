package core.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName core.filter.CheckUserFilter
 * @Author tty
 * @Date 2018\12\20 0020 13:58
 * @Version 1.0
 */
public class CheckUserFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /**
         * 拦截资源，并进行拦截处理
         *      1.要验证当前请求有没有进行登录操作
         *          1.1如果标识符是否登录 session.setAttribute("Student",stu)'
         *
         */
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        HttpSession session=request.getSession();
        String path=request.getContextPath();
        Object superuserObj=session.getAttribute("Superuser");
        Object teacherObj=session.getAttribute("Teacher");
        if (superuserObj!=null ||teacherObj!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            response.sendRedirect(path+"/Login.jsp");
        }
    }

    public void destroy() {

    }
}
