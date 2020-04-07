package Handler;

import Dao.beans.Department;
import Dao.beans.Employee;
import Dao.beans.User;
import Dao.mapper.DepartmentDao;
import Dao.mapper.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
//@RequestMapping(value = "/springmvc")
public class SpringMVCHandler {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    // 拦截器
    @RequestMapping(value = "/testInterceptor")
    public String testInterceptor(){
        return "success";
    }

    // 文件的上传
    @RequestMapping(value = "/upload")
    public String testUploadFile(@RequestParam("desc") String desc, @RequestParam("uploadFile") MultipartFile uploadFile, HttpSession session) throws IOException {
        // 获取到上传文件的名字
        String uploadFileName = uploadFile.getOriginalFilename();
        // 获取输入流
        //InputStream in = uploadFile.getInputStream();
        // 获取服务器端的uploads文件夹的真实路径
        ServletContext sc = session.getServletContext();
        String realPath = sc.getRealPath("image");
        File targetFile = new File(realPath + "/" + uploadFileName);
        //FileOutputStream os = new FileOutputStream(targetFile);
        // 写文件
//        int i;
//        while ((i = in.read()) != -1) {
//            os.write(i);
//        }
//        in.close();
//        os.close();
        uploadFile.transferTo(targetFile);
        return "success";
    }

    // 使用HttpMessageConveter完成下载功能
    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> testdownload(HttpSession session) throws IOException {
        // 将要下载的文件读取成一个字节数据
        byte[] imgs;
        ServletContext sc = session.getServletContext();
        InputStream in = sc.getResourceAsStream("image/songlaoshi.jpg");
        imgs = new byte[in.available()];
        in.read(imgs);
        // 将响应数据以及一些响应头信息封装到ResponseEntity中
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=songlaoshi.jpg");
        HttpStatus statusCode = HttpStatus.OK; // 200
        ResponseEntity<byte[]> re = new ResponseEntity<>(imgs, headers, statusCode);
        return re;
    }

    //处理Json
    @ResponseBody // 负责将方法的返回值转化成json字符串，响应给浏览器端
    @RequestMapping(value = "/testJson")
    public Collection<Employee> testJson() {
        Collection<Employee> emps = employeeDao.getAll();
        return emps;
    }

    // 修改功能：具体修改操作
    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public String updateEmp(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    // 修改功能：去往修改页面
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String toUpdatePage(@PathVariable("id") Integer id, Map<String, Object> map) {
        // 查询要修改的员工信息
        Employee employee = employeeDao.get(id);
        map.put("employee", employee);
        // 页面中显示部门下拉列表的数据
        Collection<Department> depts = departmentDao.getDepartments();
        map.put("depts", depts);
        // 页面中性别单选框的数据
        Map<String, String> genders = new HashMap<>();
        genders.put("0", "女");
        genders.put("1", "男");
        map.put("genders", genders);
        // 去往修改页面
        return "input";
    }

    // 删除功能
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String deleteEmp(@PathVariable("id") Integer id) {
        // 删除员工
        employeeDao.delete(id);
        // 重定向到列表页面
        return "redirect:/emps";
    }

    // 添加功能：具体的添加操作
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String addEmp(Employee employee) {
        // 添加员工
        employeeDao.save(employee);
        // 回到列表页面：重定向到显示所有员工信息列表的请求
        return "redirect:emps";
    }

    // 添加功能：去往添加页面，需要部门数据
    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String toAddPage(Map<String, Object> map) {
        Collection<Department> depts = departmentDao.getDepartments();
        map.put("depts", depts);
        // 构造页面中生成单选框的数据
        Map<String, String> genders = new HashMap<>();
        genders.put("0", "女");
        genders.put("1", "男");
        map.put("genders", genders);
        // 设置页面中要回显的数据
        map.put("employee", new Employee());
        return "input";
    }

    // 显示所有的员工信息列表
    @RequestMapping(value = "/emps", method = RequestMethod.GET)
    public String listAllEmps(Map<String, Object> map) {
        Collection<Employee> emps = employeeDao.getAll();
        map.put("emps", emps);
        return "list";
    }

    // 重定向：RedirectView
    @RequestMapping(value = "/testRedirectView")
    public String testRedirectView() {
        return "redirect:/ok.jsp";
    }

    // 视图：View
    @RequestMapping(value = "/testView")
    public String testView() {
        return "success";
    }

    // Model
    @RequestMapping(value = "/testMedel")
    public String testModel(Model model) {
        // 模型数据：loginMsg=用户名或密码错误
        model.addAttribute("loginMsg", "用户名或密码错误");
        return "success";
    }

    // Map
    // 结论：SpringMVC会把Map中的模型数据存放到request域对象中
    //      SpringMVC调用完请求处理方法后，不管方法的返回值是什么类型，都会处理成一个ModelAndView（参考DispatcherServlet的945行）
    @RequestMapping(value = "/testMap")
    public String testMap(Map<String, Object> map) {
        // 模型数据：password=123456
        System.out.println(map.getClass().getName());
        map.put("password", "123456");
        return "success";
    }

    // ModelAndView
    // 结论：SpringMVC会把ModelAndView中的模型数据存放到request域对象中
    @RequestMapping(value = "/testModelAndView")
    public ModelAndView testModelAndView() {
        // 模型数据：username=Admin
        ModelAndView mav = new ModelAndView();
        // 添加模型数据
        mav.addObject("username", "Admin");
        // 设置视图信息
        mav.setViewName("success");
        return mav;
    }

    // 原生的ServletAPI
    @RequestMapping(value = "/testServletAPI")
    public void testServletAPI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request); // 转发
        //request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(request,response);
        System.out.println(response); // 重定向，或者将数据写给客户端
        //response.sendRedirect("http://www.baidu.com");
        response.getWriter().println("Hello SpringMVC");
    }

    // POJO
    @RequestMapping(value = "/testPOJO")
    public String testPOJO(User user) {
        System.out.println(user);
        return "success";
    }

    // @CookieValue：映射cookie信息到请求处理方法的形参中
    @RequestMapping(value = "/testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String sessionId) {
        System.out.println(sessionId);
        return "success";
    }

    // @RequestHeader：映射请求头信息到请求处理方法的形参中
    @RequestMapping(value = "/testRequestHeader")
    public String testRequestHeader(@RequestHeader("Accept-Language") String acceptLanguage) {
        System.out.println(acceptLanguage);
        return "success";
    }

    // @RequestParam：映射请求参数到请求处理方法的形参
    @RequestMapping(value = "/testRequestParam")
    public String testRequestParam(@RequestParam("username") String username, @RequestParam("age") Integer age) {
        // web:request.getParameter() request.getParameterMap()
        System.out.println(username + ":" + age);
        return "success";
    }

    // REST PUT
    @RequestMapping(value = "/order", method = RequestMethod.PUT)
//    @ResponseBody
    public String testRestPUT() {
        System.out.println("REST PUT");
        return "success";
    }

    // REST POST
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String testRestPOST() {
        System.out.println("REST POST");
        return "success";
    }

    // REST DELETE
    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
//    @ResponseBody
    public String testRestDELETE(@PathVariable("id") Integer id) {
        System.out.println("REST DELETE:" + id);
        return "success";
    }

    // REST GET
    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public String testRestGET(@PathVariable("id") Integer id) {
        System.out.println("REST GET:" + id);
        return "success";
    }

    @RequestMapping(value = "/testPathVariable/{name}/{id}")
    public String testPathVariable(@PathVariable("name") String name, @PathVariable("id") Integer id) {
        System.out.println(name + ":" + id);
        return "success";
    }

    @RequestMapping(value = "/testRequestMappingParamsAndHanders", params = {"username=Tom", "age=22"}, headers = {"Accept-Language"})
    public String testRequestMappingParamsAndHanders() {
        return "success";
    }

    @RequestMapping(value = "/testRequestMappingMethod", method = {RequestMethod.POST, RequestMethod.GET})
    public String testRequestMappingMethod() {
        return "success";
    }

    @RequestMapping(value = "/testRequestMapping")
    public String testRequestMapping() {
        return "success";
    }

}
