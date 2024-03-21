package sb;

import entities.Department;
import entities.Employee;
import jakarta.ejb.Local;
import java.util.List;

@Local
public interface HomeSBLocal {

    public Employee checkLogin(String id, String pass);

    public List<Department> getDepartment();

    public List<Employee> findAll();

    public Employee findOne(String id);

    public void saveEmployee(Employee newEmployee);
    
    public void resetPassword(String id, String newPassword);
}
