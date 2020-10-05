package ir.maktab.repository.Impl;

import ir.maktab.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.entities.Employee;
import ir.maktab.repository.EmployeeRepository;
import ir.maktab.services.CustomerService;
import ir.maktab.services.EmployeeService;

import javax.persistence.TypedQuery;

public class EmployeeRepositoryImpl extends BaseRepositoryImpl<Employee,Integer> implements EmployeeRepository {
    @Override
    protected Class<Employee> getEntityClass() {
        return Employee.class;
    }

    @Override
    public boolean login(String userName, String password) {
        em.getTransaction().begin();
        TypedQuery<Employee> query = em.createQuery(
                "SELECT u FROM Employee u where u.name=:code and u.password=:pass",
                Employee.class);
        query.setParameter("code", userName);
        query.setParameter("pass", password);
        if (query.getResultList().size() > 0) {
            Employee u = query.getSingleResult();
            EmployeeService.setEmployer(u);
            System.out.println("Welcome BaCk: " + u.getName());
            em.getTransaction().commit();
            return true;
        } else {
            em.getTransaction().rollback();
            return false;
        }
    }
}
