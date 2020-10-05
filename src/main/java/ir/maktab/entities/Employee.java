package ir.maktab.entities;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" , updatable = false , nullable = false , unique = true)
    private int id;
    @Column(name = "name", nullable = false , unique = true)
    private String name;
    @Column(name = "password" , nullable = false )
    private String password;
    @ManyToOne
    private Role role;
    @ManyToOne
    @JoinColumn(name = "branch")
    private Branch branch;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", branch=" + branch +
                '}';
    }
}
