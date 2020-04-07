package Login_Servlet;

import Dao.EmployeeDao;
import Dao.EmployeeDaoImpl;
import Dao.UserDao;
import Dao.UserDaoImpl;
import Login_Beans.Employee;
import Login_Beans.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 登录功能的实现
        System.out.println("登录请求过来了");
        req.setCharacterEncoding("utf-8");
        // 获取到用户输入的用户名和密码，进行登陆业务的处理
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username + "," + password);
        // 验证用户名和密码是否正确
        // 通过响应对象，给客户端响应数据
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        // 获取Dao对象
        UserDao userDao = new UserDaoImpl();
        User user = null;
        try {
            user = userDao.getUserByUsernameAndPassword(username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(user==null){
            // 登陆失败
            // 通过重定向的方式去往登录页面
            // resp.sendRedirect("login.jsp");
            // 转发
            // 绑定数据，就是将想要交给下一个组件（JSP）处理的数据，绑定到request对象中
            req.setAttribute("login_msg","用户名或密码错误！");
            // 获取转发器
            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
            // 开始转发
            rd.forward(req,resp);
        }else {
            // 登陆成功
            // 登录用户设置到session中
            HttpSession session = req.getSession();
            session.setAttribute("loginUser",user);
            // 查所有的员工数据
            EmployeeDao employeeDao = new EmployeeDaoImpl();
            List<Employee> emps = employeeDao.selectAllEmps();
            // 转发之前绑定数据
            req.setAttribute("emps",emps);
            // 转发
            req.getRequestDispatcher("main.jsp").forward(req,resp);
        }
    }
}
