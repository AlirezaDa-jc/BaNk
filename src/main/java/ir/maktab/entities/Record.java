package ir.maktab.entities;

import javax.persistence.*;

@Entity
@Table(name = "record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" , updatable = false , nullable = false , unique = true)
    private int id;
    @Column(name = "createDate",updatable = false, nullable = false)
    private String createDate;
    @Column(name = "record" , updatable = false , nullable = false)
    private String record;
    @Column(name = "isDone" , nullable = false)
    private boolean isDone;
    @ManyToOne
    @JoinColumn(name="customerid")
    private Customer customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Record{" +
                "createDate='" + createDate + '\'' +
                ", record='" + record + '\'' +
                ", isDone=" + isDone +
                '}';
    }
}
