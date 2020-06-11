package br.com.rjsystems.myblog.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Login.class)
public abstract class Login_ {

	public static volatile SingularAttribute<Login, String> password;
	public static volatile SingularAttribute<Login, String> name;
	public static volatile ListAttribute<Login, Permition> permitions;
	public static volatile SingularAttribute<Login, Long> id;
	public static volatile SingularAttribute<Login, String> email;

	public static final String PASSWORD = "password";
	public static final String NAME = "name";
	public static final String PERMITIONS = "permitions";
	public static final String ID = "id";
	public static final String EMAIL = "email";

}

