package br.com.rjsystems.myblog.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Post.class)
public abstract class Post_ {

	public static volatile SingularAttribute<Post, Author> author;
	public static volatile SingularAttribute<Post, String> topic;
	public static volatile SingularAttribute<Post, Long> id;
	public static volatile SingularAttribute<Post, String> text;
	public static volatile SingularAttribute<Post, String> title;
	public static volatile SingularAttribute<Post, LocalDate> publicationDate;
	public static volatile SingularAttribute<Post, String> subtopic;

	public static final String AUTHOR = "author";
	public static final String TOPIC = "topic";
	public static final String ID = "id";
	public static final String TEXT = "text";
	public static final String TITLE = "title";
	public static final String PUBLICATION_DATE = "publicationDate";
	public static final String SUBTOPIC = "subtopic";

}

