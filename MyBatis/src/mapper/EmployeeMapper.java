package mapper;

import beans.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    // 定义CRUD相关的方法
    // 根据id查询Employee
    public Employee getEmployeeById(Integer id);

    // 添加一个新的Employee
    public void addEmployee(Employee employee);

    // 修改一个Employee
    public void updateEmployee(Employee employee);

    // 删除一个Employee
    public Integer deleteEmployeeById(Integer id);

    public Employee getEmployeeByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    public Employee getEmployeeByMap(Map<String, Object> map);

    // 查询多行数据返回一个对象的集合
    public List<Employee> getEmps();

    // 查询单条数据返回一个Map
    public Map<String, Object> getEmployeeByIdRetuenMap(Integer id);

    // 查询多条数据返回一个Map
    @MapKey("id")  // 指定使用对象的哪个属性作为Map的key
    public Map<Integer, Employee> getEmpsRetuenMap();

}
