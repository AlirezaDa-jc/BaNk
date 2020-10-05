package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entities.Employee;

public interface EmployeeRepository extends BaseRepository<Employee,Integer> {
    boolean login(String userName, String password);
}
