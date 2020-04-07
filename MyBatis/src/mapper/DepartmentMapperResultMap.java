package mapper;

import beans.Department;

public interface DepartmentMapperResultMap {

    public Department getDeptById(Integer id);

    public Department getDeptAndEmps(Integer id);

    public Department getDeptAndEmpsStep(Integer id);

}
