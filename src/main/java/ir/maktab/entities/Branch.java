package ir.maktab.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" , updatable = false , nullable = false , unique = true)
    private int id;
    @Column(name = "title" , nullable = false , unique = true)
    private String title;
    @Column(name = "description" , nullable = false , unique = true)
    private String description;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Employee boss;

    @OneToMany(mappedBy = "branch",cascade = CascadeType.ALL)
    private Set<Account> accounts = new HashSet<>();
    @OneToMany(mappedBy = "branch",cascade = CascadeType.ALL)
    private Set<Employee> employees = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
    public void addAccount(Account account) {
        accounts.add(account);
        account.setBranch(this);
    }
    public void addEmployer(Employee employee) {
        employees.add(employee);
        employee.setBranch(this);
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Employee getBoss() {
        return boss;
    }

    public void setBoss(Employee boss) {
        this.boss = boss;
    }
}
