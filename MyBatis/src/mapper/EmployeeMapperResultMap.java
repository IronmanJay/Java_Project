package mapper;

import beans.Employee;

import java.util.List;

// 自定义映射
public interface EmployeeMapperResultMap {

    public Employee getEmployeeById(Integer id);

    public Employee getEmpAndDept(Integer id);

    public Employee getEmpAndDeptStep(Integer id);

    public List<Employee> getEmpsByDid(Integer id);

}
