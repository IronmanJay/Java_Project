package mapper;

import beans.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapperDynamicSQL {

    public List<Employee> getEmpsByConditionIfWhere(Employee Condition);

    public List<Employee> getEmpsByConditionTrim(Employee Condition);

    public void updateEmpByConditionSet(Employee Condition);

    public List<Employee> getEmpsByConditionChoose(Employee Condition);

    public List<Employee> getEmpsByIds(@Param("ids")List<Integer> ids);

    // 批量操作：修改 删除 添加
    public void  addEmps(@Param("emps")List<Employee> emps );

}
