package sb;

import entities.Department;
import entities.Employee;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.List;

@Stateless
public class HomeSB implements HomeSBLocal {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab5PU");
    EntityManager em = emf.createEntityManager();

    @Override
    public Employee checkLogin(String id, String pass) {
        Employee employee = null;
        try {
            String sql = "SELECT e FROM Employee e WHERE e.id = :id AND e.password = :password";
            em.getTransaction().begin();
            employee = em.createQuery(sql, Employee.class).setParameter("id", id).setParameter("password", pass).getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
        }
        return employee;
    }

    @Override
    public List<Department> getDepartment() {

        return em.createNamedQuery("Department.findAll", Department.class).getResultList();

    }

    @Override
    public List<Employee> findAll() {

        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();

    }

    @Override
    public Employee findOne(String id) {

        try {
            em.getTransaction().begin();
            Employee employee = em.find(Employee.class, id);
            em.getTransaction().commit();
            if (employee != null) {
                return employee;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
        }

        return null;

    }

    @Override
    public void saveEmployee(Employee newEmployee) {
        Persist(newEmployee);
    }

    public void Persist(Object object) {

        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
        }
    }

    @Override
    public void resetPassword(String id, String newPassword) {
        Employee employee = em.find(Employee.class, id);
        if (employee != null) {
            em.getTransaction().begin();
            employee.setPassword(newPassword);
            em.merge(employee);
            em.getTransaction().commit();
        } else {
            System.out.println("Employee Not Found With ID: " + id);
        }
    }
}
