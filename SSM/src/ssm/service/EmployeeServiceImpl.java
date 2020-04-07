package ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.beans.Employee;
import ssm.mapper.EmployeeMapper;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper ;

    @Override
    public List<Employee> getAllEmps() {
        return employeeMapper.getAllEmps();
    }

}
