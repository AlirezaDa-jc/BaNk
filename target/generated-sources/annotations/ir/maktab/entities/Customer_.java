package ir.maktab.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customer.class)
public abstract class Customer_ {

	public static volatile SingularAttribute<Customer, String> birthday;
	public static volatile SingularAttribute<Customer, String> nationalCode;
	public static volatile SingularAttribute<Customer, String> password;
	public static volatile SingularAttribute<Customer, Boolean> isSuspended;
	public static volatile SingularAttribute<Customer, Role> role;
	public static volatile SingularAttribute<Customer, Address> address;
	public static volatile SetAttribute<Customer, Record> records;
	public static volatile SingularAttribute<Customer, String> name;
	public static volatile SingularAttribute<Customer, Integer> id;
	public static volatile SetAttribute<Customer, Account> Accounts;

}

