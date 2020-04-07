package ssm.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ssm.beans.Employee;
import ssm.service.EmployeeService;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeService employeeService ;

    /**
     * 显示所有的员工信息
     */
    @RequestMapping(value="/emps" ,method=RequestMethod.GET)
    public String  listAllEmps(Map<String, Object> map) {
        List<Employee> emps = employeeService.getAllEmps();
        map.put("emps", emps);
        return "list";
    }
}
