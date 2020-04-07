package Login_Servlet;

import Dao.UserDao;
import Dao.UserDaoImpl;
import Login_Beans.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 注册业务处理：
        // 1、获取到用户提交的注册信息
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 2、判断用户名是否可用
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByUsername(username);
        if (user != null) {
            // 注册失败，回到注册页面，并进行相应的提示
            // 转发
            req.setAttribute("regist_msg","用户名已经存在");
            req.getRequestDispatcher("regist.jsp").forward(req,resp);
        } else {
            // 可以注册
            // 3、将用户注册的信息插入到数据库
            userDao.insertUser(username,password);
            // 注册成功去往登陆页面
            resp.sendRedirect("login.jsp");
        }
    }
}
