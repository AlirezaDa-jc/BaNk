package ir.maktab.services;

import ir.maktab.MainApp;
import ir.maktab.Scan;
import ir.maktab.entities.Branch;
import ir.maktab.entities.Employee;
import ir.maktab.entities.Role;
import ir.maktab.repository.EmployeeRepository;
import ir.maktab.repository.Impl.EmployeeRepositoryImpl;

import java.util.List;

public class EmployeeService {

    private static Employee employee;
    private static EmployeeRepository repository = new EmployeeRepositoryImpl();
    private static Scan sc = MainApp.getSc();

    public static Employee getEmployer() {
        return employee;
    }

    public static void setEmployer(Employee employee) {
        EmployeeService.employee = employee;
    }

    public static boolean login() {
        String userName = sc.getString("UserName: ");
        String password = sc.getString("Password: ");

        //            admin.setName(userName);
        //            admin.setPassword(password);
        //            Role role = RoleService.getRole();
        //            admin.setRole(role);
        //            role.addAdmin(admin);
        return repository.login(userName, password);
    }

    public static Employee insertBoss() {
        Employee employee = addInputs();
        repository.insert(employee);
        return employee;
    }

    private static Employee addInputs() {
        Employee employee = new Employee();
        String userName = sc.getString("Username: ");
        String password = sc.getString("Password: ");
        employee.setName(userName);
        employee.setPassword(password);
        Role role = RoleService.getRole();
        employee.setRole(role);
        role.addAdmin(employee);
        return employee;
    }

    public static void displayAll() {
        List<Employee> all = repository.findAll();
        all.forEach(System.out::println);
    }

    public static void updateBranch(Employee boss) {
        repository.update(boss);
    }

    public static Employee findById(int id) {
        return repository.findById(id);
    }

    public static void insert() {
        Branch branch =BranchService.use();
        Employee employee = addInputs();
        employee.setBranch(branch);
        Employee boss = branch.getBoss();
        employee.setBoss(boss);
        boss.addEmployee(employee);
        branch.addEmployee(employee);
        repository.insert(employee);
        repository.update(boss);
    }

    public static void displayEmployees() {
        List<Employee> all = repository.findAll();
        all.stream()
                .filter((b)-> b.getBoss() != null && b.getBoss().getName().equals(employee.getName()))
                .forEach(System.out::println);
    }
}
