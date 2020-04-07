package test;

import beans.Employee;
import mapper.EmployeeMapperDynamicSQL;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TestMybatisDynamicSQL {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    @Test
    public void testIf() throws IOException {
        SqlSessionFactory ssf = getSqlSessionFactory();
        SqlSession session = ssf.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
            Employee condition = new Employee();
            condition.setId(1012);
//            condition.setLastName("Tom");
//            condition.setEmail("pp@qq.com");
//            condition.setGender(0);
            List<Employee> emps = mapper.getEmpsByConditionIfWhere(condition);
            System.out.println(emps);
        }finally {
            session.close();
        }
    }

    @Test
    public void testTrim() throws IOException {
        SqlSessionFactory ssf = getSqlSessionFactory();
        SqlSession session = ssf.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
            Employee condition = new Employee();
            condition.setId(1012);
            condition.setLastName("Tom");
            condition.setEmail("pp@qq.com");
            condition.setGender(0);
            List<Employee> emps = mapper.getEmpsByConditionTrim(condition);
            System.out.println(emps);
        }finally {
            session.close();
        }
    }

    @Test
    public void testset() throws IOException {
        SqlSessionFactory ssf = getSqlSessionFactory();
        SqlSession session = ssf.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
            Employee condition = new Employee();
            condition.setId(1012);
            condition.setLastName("TomAA");
            condition.setEmail("tomaa@qq.com");
//            condition.setGender(0);
            mapper.updateEmpByConditionSet(condition);
            session.commit();
        }finally {
            session.close();
        }
    }

    @Test
    public void testChoose() throws IOException {
        SqlSessionFactory ssf = getSqlSessionFactory();
        SqlSession session = ssf.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
            Employee condition = new Employee();
//            condition.setId(1012);
//            condition.setLastName("TomAA");
//            condition.setEmail("tomaa@qq.com");
//            condition.setGender(0);
            List<Employee> emps = mapper.getEmpsByConditionChoose(condition);
            System.out.println(emps);
        }finally {
            session.close();
        }
    }

    @Test
    public void testForeach() throws IOException {
        SqlSessionFactory ssf = getSqlSessionFactory();
        SqlSession session = ssf.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
            List<Integer> ids = new ArrayList<Integer>();
            ids.add(1002);
            ids.add(1003);
            ids.add(1004);
            ids.add(1005);
            List<Employee> emps = mapper.getEmpsByIds(ids);
            System.out.println(emps);
        }finally {
            session.close();
        }
    }

    @Test
    public void testBacth() throws IOException {
        SqlSessionFactory ssf = getSqlSessionFactory();
        SqlSession session = ssf.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee> emps = new ArrayList<>();
            emps.add(new Employee(null,"AA","aa@qq.com",1));
            emps.add(new Employee(null,"AA","AA@QQ.COM",0));
            emps.add(new Employee(null,"AA","AA@QQ.COM",1));
            mapper.addEmps(emps);
            session.commit();
        }finally {
            session.close();
        }
    }

}
