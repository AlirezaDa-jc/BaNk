package ir.maktab.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Account.class)
public abstract class Account_ {

	public static volatile SingularAttribute<Account, Integer> balance;
	public static volatile SingularAttribute<Account, Boolean> isDeleted;
	public static volatile SingularAttribute<Account, Integer> id;
	public static volatile SingularAttribute<Account, Branch> branch;
	public static volatile SingularAttribute<Account, Card> card;
	public static volatile SingularAttribute<Account, String> createDate;
	public static volatile SingularAttribute<Account, Customer> customer;

}

