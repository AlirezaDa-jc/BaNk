package ir.maktab.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Employee.class)
public abstract class Employee_ {

	public static volatile SingularAttribute<Employee, String> password;
	public static volatile SingularAttribute<Employee, Role> role;
	public static volatile SingularAttribute<Employee, Employee> boss;
	public static volatile SingularAttribute<Employee, String> name;
	public static volatile SingularAttribute<Employee, Integer> id;
	public static volatile SetAttribute<Employee, Employee> employees;
	public static volatile SingularAttribute<Employee, Branch> branch;

}

