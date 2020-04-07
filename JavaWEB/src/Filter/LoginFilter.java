package Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    // 真正的过滤处理
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 当前的请求是谁
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String url = req.getRequestURL().toString();
        // 判断要过滤的请求
        if(url.endsWith("main.jsp")){
            // 过滤，引导去往登录页面
            resp.sendRedirect("login.jsp");
        }else {
            // 放行，继续执行后续的处理
            // FilterChain：过滤器链对象
            filterChain.doFilter(req,resp);
        }
    }


    // 初始化方法
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoginFilter初始化完成");
    }

    // 销毁方法
    @Override
    public void destroy() {
        System.out.println("LoginFilter销毁完成");
    }

}
