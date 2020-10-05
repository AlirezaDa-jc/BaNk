package ir.maktab.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private int id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "nationalCode", nullable = false, unique = true)
    private String nationalCode;
    @Column(name = "birthday", nullable = false)
    private String birthday;
    @Column(name = "password")
    private String password;
    @Column(name = "isSuspended")
    private boolean isSuspended = false;


    @ManyToOne
    @JoinColumn(name = "roleid")
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy="customer",cascade = CascadeType.ALL)
    private Set<Record> records = new HashSet<>();

    @OneToMany(mappedBy="customer",cascade = CascadeType.ALL)
    private Set<Account> Accounts = new HashSet<>();

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Account> getAccounts() {
        return Accounts;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAccounts(Set<Account> accounts) {
        this.Accounts = accounts;
    }

    public void addAccount(Account account) {
        Accounts.add(account);
    }

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

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }
}
