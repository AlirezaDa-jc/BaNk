package ir.maktab.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Branch.class)
public abstract class Branch_ {

	public static volatile SingularAttribute<Branch, String> description;
	public static volatile SingularAttribute<Branch, Integer> id;
	public static volatile SetAttribute<Branch, Account> accounts;
	public static volatile SingularAttribute<Branch, String> title;
	public static volatile SetAttribute<Branch, Employee> employees;

}

