package Dao;

import Login_Beans.Department;
import Login_Beans.Employee;
import utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{

    @Override
    public List<Employee> selectAllEmps() {
        List<Employee> emps = new ArrayList<Employee>();
        try {
            Connection conn = ConnectionUtils.getConn();
            String sql = "select e.id eid, e.last_name, e.email,e.gender,d.id did ,d.dept_name " + " from  tb1_employee e , tb1_dept d  where e.d_id = d.id ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getInt("eid"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));
                employee.setGender(rs.getInt("gender"));

                Department dept = new Department();
                dept.setId(rs.getInt("did"));
                dept.setDeptName(rs.getString("dept_name"));

                employee.setDept(dept);

                emps.add(employee);
            }
            return emps;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionUtils.closeConn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
