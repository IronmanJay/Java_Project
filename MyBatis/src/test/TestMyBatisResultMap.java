package test;

import beans.Department;
import beans.Employee;
import mapper.DepartmentMapperResultMap;
import mapper.EmployeeMapperResultMap;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;

public class TestMyBatisResultMap {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    @Test
    public void testResultMap() throws IOException {
        SqlSessionFactory ssf = getSqlSessionFactory();
        SqlSession session = ssf.openSession();
        try {
            EmployeeMapperResultMap mapper = session.getMapper(EmployeeMapperResultMap.class);
            Employee employee = mapper.getEmployeeById(1005);
            System.out.println(employee);
        } finally {
            session.close();
        }
    }

    @Test
    public void testResultMapCascade() throws IOException {
        SqlSessionFactory ssf = getSqlSessionFactory();
        SqlSession session = ssf.openSession();
        try {
            EmployeeMapperResultMap mapper = session.getMapper(EmployeeMapperResultMap.class);
            Employee employee = mapper.getEmpAndDept(1005);
            System.out.println(employee);
            System.out.println(employee.getDept());
        } finally {
            session.close();
        }
    }

    @Test
    public void testResultMapAssociat() throws IOException {
        SqlSessionFactory ssf = getSqlSessionFactory();
        SqlSession session = ssf.openSession();
        try {
            EmployeeMapperResultMap mapper = session.getMapper(EmployeeMapperResultMap.class);
            Employee employee = mapper.getEmpAndDeptStep(1005);
            System.out.println(employee.getLastName());
            System.out.println("+++++++++++++++++++++");
            System.out.println(employee.getDept());
        } finally {
            session.close();
        }
    }

    @Test
    public void testResultMapCollection() throws IOException {
        SqlSessionFactory ssf = getSqlSessionFactory();
        SqlSession session = ssf.openSession();
        try {
            DepartmentMapperResultMap mapper = session.getMapper(DepartmentMapperResultMap.class);
            Department department = mapper.getDeptAndEmps(4);
            System.out.println(department);
            System.out.println(department.getEmps());
        } finally {
            session.close();
        }
    }

    @Test
    public void testResultMapCollectionStep() throws IOException {
        SqlSessionFactory ssf = getSqlSessionFactory();
        SqlSession session = ssf.openSession();
        try {
            DepartmentMapperResultMap mapper = session.getMapper(DepartmentMapperResultMap.class);
            Department department = mapper.getDeptAndEmpsStep(4);
            System.out.println(department.getDepartmentName());
            System.out.println(department.getEmps());
        } finally {
            session.close();
        }
    }

}
