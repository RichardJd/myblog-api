package br.com.rjsystems.myblog.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Author.class)
public abstract class Author_ {

	public static volatile SingularAttribute<Author, String> name;
	public static volatile SingularAttribute<Author, Long> id;
	public static volatile SingularAttribute<Author, String> avatar;
	public static volatile SingularAttribute<Author, String> biography;
	public static volatile SingularAttribute<Author, String> login;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String AVATAR = "avatar";
	public static final String BIOGRAPHY = "biography";
	public static final String LOGIN = "login";

}

