package ir.maktab.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Card.class)
public abstract class Card_ {

	public static volatile SingularAttribute<Card, String> cvv2;
	public static volatile SingularAttribute<Card, String> password;
	public static volatile SingularAttribute<Card, String> cardId;
	public static volatile SingularAttribute<Card, Integer> id;
	public static volatile SingularAttribute<Card, Account> account;

}

