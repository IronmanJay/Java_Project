package Jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate npjt;

    public void insertEmployee(Employee employee){
        String sql = "insert into tb1_employee(last_name,email,gender) value(:lastName,:email,:gender)";
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(employee);
        npjt.update(sql,parameterSource);
    }

}
