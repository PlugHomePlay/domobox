package fr.infini.domobox.utils.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.google.common.base.Preconditions;

public class GenericDaoImpl<E extends Dto<?>, ID extends Serializable> {

	private final Class<E> persistentClass;
	
	private EntityManager entityManager;
	
	/**
	 * Constructs a new {@link GenericDaoImpl} instance. Figures out the actual class 
	 * &lt;E&gt; of the persistent entity.
	 */
	@SuppressWarnings("unchecked")
	public GenericDaoImpl(){
		ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
		this.persistentClass = (Class<E>) genericSuperClass.getActualTypeArguments()[0];
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("domobox");
		entityManager = factory.createEntityManager();
	}
	
	public E get(ID id){
		Preconditions.checkNotNull(id, "Got unexpected null 'id' passed to the method.");
		
		return entityManager.find(persistentClass, id);
	}
	
	public E save(E entity){
		Preconditions.checkNotNull(entity, "Got unexpected null 'entity' passed to the method.");
		
		if(entity.getId() == null) {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
		} else {
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
		}
		return entity;
	}
	
	public ArrayList<E> getAll(){
		final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		final CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(this.persistentClass);
		final Root<E> root = criteriaQuery.from(this.persistentClass);
		criteriaQuery.select(root);
		
		final TypedQuery<E> query = this.entityManager.createQuery(criteriaQuery);
		
		return new ArrayList<E>(query.getResultList());
	}
	
	public void delete(E entity){
		Preconditions.checkNotNull(entity, "Got unexpected null 'entity' passed to the method.");
		
		entityManager.getTransaction().begin();
		entity = this.entityManager.merge(entity);
		this.entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<E> findWhithNamedQuery(String namedQuery) {
		return this.entityManager.createNamedQuery(namedQuery).getResultList();
	}
	
	public List<E> findWhithNamedQuery(String namedQuery, int resultLimit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<E> findWhithNamedQuery(String namedQuery, Map<String, Object> parameters) {
		return findWhithNamedQuery(namedQuery, parameters, 0);
	}
	
	public List<E> findWhithNamedQuery(String namedQuery, Map<String, Object> parameters, int resultLimits) {
		Set<Entry<String, Object>> rawParameters = parameters.entrySet();
		TypedQuery<E> requete = this.entityManager.createNamedQuery(namedQuery, persistentClass);
		
		if(resultLimits > 0){
			requete.setMaxResults(resultLimits);
		}
		
		for(Entry<String, Object> entry : rawParameters){
			requete.setParameter(entry.getKey(), entry.getValue());
		}
		
		return requete.getResultList();
	}

    public E findOneWhithNamedQuery(String namedQuery, Map<String, Object> parameters) {
		return findOneWhithNamedQuery(namedQuery, parameters, 0);
    }

    public E findOneWhithNamedQuery(String namedQuery, Map<String, Object> parameters, int resultLimits) {
		Set<Entry<String, Object>> rawParameters = parameters.entrySet();
		TypedQuery<E> requete = this.entityManager.createNamedQuery(namedQuery, persistentClass);
		
		if(resultLimits > 0){
			requete.setMaxResults(resultLimits);
		}
		
		for(Entry<String, Object> entry : rawParameters){
			requete.setParameter(entry.getKey(), entry.getValue());
		}
		
		return (E) requete.getSingleResult();
    }
}
