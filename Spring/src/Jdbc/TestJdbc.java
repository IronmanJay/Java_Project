package Jdbc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJdbc {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate npjt;

    private EmployeeDao employeeDao;

    @Before
    public void init() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
        npjt = ctx.getBean("namedParameterJdbcTemplate", NamedParameterJdbcTemplate.class);
        employeeDao = ctx.getBean("employeeDao",EmployeeDao.class);
    }

    // update：增删改操作
    @Test
    public void testUpdate() {
        String sql = "insert into tb1_employee(last_name,email,gender) value(?,?,?)";
        //jdbcTemplate.update(sql, "魏老师", "wls@qq.com", 1);
        jdbcTemplate.update(sql, new Object[]{"魏老师", "wls@qq.com", 1});
    }

    // batchUpdate：批量增删改
    @Test
    public void testBatchUpdate() {
        String sql = "insert into tb1_employee(last_name,email,gender) value(?,?,?)";
        List<Object[]> batchArgs = new ArrayList<Object[]>();
        batchArgs.add(new Object[]{"赵越", "123@qq.com", 1});
        batchArgs.add(new Object[]{"柏蕊", "456@qq.com", 2});
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    // queryForObject
    // 1、查询单行数据，返回一个对象
    // 2、查询单值，返回单个值
    @Test
    public void testQueryForObjectReturnObject() {
        String sql = "select id,last_name,email,gender from tb1_employee where id = ?";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, 1001);
        System.out.println(employee);
    }

    @Test
    public void testQueryForObjectReturnValue() {
        String sql = "select count(id) from tb1_employee";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(result);
    }

    // query：查询多条数据，返回多个对象的集合
    @Test
    public void testQuery() {
        String sql = "select id,last_name,email,gender from tb1_employee";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        List<Employee> emps = jdbcTemplate.query(sql, rowMapper);
        System.out.println(emps);
    }

    // 测试具名参数模板类
    @Test
    public void testNpjt() {
        String sql = "insert into tb1_employee(last_name,email,gender) values(:ln,:em,:ge)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ln", "刘德华");
        paramMap.put("em", "ldh@qq.com");
        paramMap.put("ge", 0);
        npjt.update(sql, paramMap);
    }

    @Test
    public void testNpjtObject(){
        // 模拟Service层直接传递给Dao层一个具体的对象
        Employee employee = new Employee(null,"张无忌","zwj@qq.com",1);
        // 在dao的插入方法中
        String sql = "insert into tb1_employee(last_name,email,gender) values(:lastName,:email,:gender)";
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(employee);
        npjt.update(sql,parameterSource);
    }

    // 测试EmployeeDao
    @Test
    public void testEmployeeDao(){
        Employee employee = new Employee(null,"莫小贝","zwj@qq.com",1);
        employeeDao.insertEmployee(employee);
    }

}
