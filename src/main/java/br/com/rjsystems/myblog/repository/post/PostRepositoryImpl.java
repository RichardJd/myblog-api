package br.com.rjsystems.myblog.repository.post;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.com.rjsystems.myblog.model.Post;
import br.com.rjsystems.myblog.model.Post_;
import br.com.rjsystems.myblog.repository.filter.PostFilter;

public class PostRepositoryImpl implements PostRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Post> filtrate(PostFilter postFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Post> criteria = builder.createQuery(Post.class);

		Root<Post> root = criteria.from(Post.class);

		Predicate[] predicates = createRestrictions(postFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Post> query = manager.createQuery(criteria);
		addRestrictionsPagination(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(postFilter));
	}

	private Predicate[] createRestrictions(PostFilter postFilter, CriteriaBuilder builder, Root<Post> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(postFilter.getTitle())) {
			predicates.add(builder.like(builder.lower(root.get(Post_.title)),
					"%" + postFilter.getTitle().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(postFilter.getTopic())) {
			predicates.add(builder.like(builder.lower(root.get(Post_.topic)),
					"%" + postFilter.getTopic().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(postFilter.getSubtopic())) {
			predicates.add(builder.like(builder.lower(root.get(Post_.subtopic)),
					"%" + postFilter.getSubtopic().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void addRestrictionsPagination(TypedQuery<Post> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int totalRecords = pageable.getPageSize();
		int firstPageRecord = currentPage * totalRecords;

		query.setFirstResult(firstPageRecord);
		query.setMaxResults(totalRecords);
	}

	private Long total(PostFilter postFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Post> root = criteria.from(Post.class);
		
		Predicate[] predicates = createRestrictions(postFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

}
