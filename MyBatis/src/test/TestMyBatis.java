package test;

import beans.Employee;
import mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class TestMyBatis {

    @Test
    public void testSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        System.out.println(sqlSessionFactory);
        SqlSession session = sqlSessionFactory.openSession();
        System.out.println(session);
        try {
            Employee employee = session.selectOne("suibian.selectEmployee", 1001);
            System.out.println(employee);
        } finally {
            session.close();
        }
    }

    @Test
    public void testHelloWorldMapper() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // mapper接口:dao接口
            // 获取MyBatis为Mapper接口生成的代理实现类对象
            EmployeeMapper dao = session.getMapper(EmployeeMapper.class);
            System.out.println(dao.getClass().getName());
            Employee employee = dao.getEmployeeById(1001);
            System.out.println(employee);
        } finally {
            session.close();
        }
    }

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    @Test
    public void testCRUD() throws IOException {
        SqlSessionFactory ssf = getSqlSessionFactory();
        SqlSession session = ssf.openSession();
//        ssf.openSession(true); 自动提交
        try {
            // 获取Mapper接口的代理实现类对象
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            // 查询
//            Employee employee = mapper.getEmployeeById(1005);
//            System.out.println(employee);
            // 添加
//            Employee employee = new Employee(null, "苍老师", "csl@qq.com", 0);
//            mapper.addEmployee(employee);
//            System.out.println("返回的主键值:" + employee.getId());
            // 修改
//            Employee employee = new Employee(1008, "敏敏", "mm@qq.com", 0);
//            mapper.updateEmployee(employee);
            // 删除
//            Integer result = mapper.deleteEmployeeById(1001);
//            System.out.println(result);
            // 提交
            session.commit();
        } finally {
            session.close();
        }
    }

    @Test
    public void testParameter() throws IOException {
        SqlSessionFactory ssf = getSqlSessionFactory();
        SqlSession session = ssf.openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmployeeByIdAndLastName(1022,"小泽老师");
            System.out.println(employee);
//            Map<String,Object> map = new HashMap<>();
//            map.put("id",1022);
//            map.put("ln","小泽老师");
//            Employee employee = mapper.getEmployeeByMap(map);
//            System.out.println(employee);
        }finally {
            session.close();
        }
    }

    @Test
    public void testSelect() throws IOException {
        SqlSessionFactory ssf = getSqlSessionFactory();
        SqlSession session = ssf.openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
//            List<Employee> emps = mapper.getEmps();
//            System.out.println(emps);
//            Map<String,Object> map = mapper.getEmployeeByIdRetuenMap(1005);
//            System.out.println(map);
            Map<Integer,Employee> map = mapper.getEmpsRetuenMap();
            System.out.println(map);
        }finally {
            session.close();
        }
    }

}
