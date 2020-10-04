package ir.maktab.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" , updatable = false , nullable = false , unique = true)
    private int id;
    @Column(name = "title" , nullable = false)
    private String roleTitle;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private Set<Customer> customers = new HashSet<>();

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private Set<Employee> employees = new HashSet<>();

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        customer.setRole(this);
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void addAdmin(Employee employee){
        employees.add(employee);
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String role) {
        this.roleTitle = role;
    }
}
