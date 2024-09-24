package com.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.entity.CustomerProfileEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class CustomerProfileDAOImpl {

	@PersistenceContext
	private EntityManager entityManager;

	public CustomerProfileEntity getCustomerProfileById(Integer customerId) {
		String queryString = "SELECT c FROM CustomerProfileEntity c WHERE c.customerId = :customerId ";

		List<CustomerProfileEntity> result = entityManager.createQuery(queryString, CustomerProfileEntity.class)
				.setParameter("customerId", customerId).getResultList();
		
		if(result!=null&&result.size()>0) {
			return result.get(0);
		} else {
			return null;
		}
	}
	
	public Boolean checkIsUserIdExists(String userId, Integer customerId) {
		String queryString = "SELECT c FROM CustomerProfileEntity c WHERE UPPER(c.userId) = :userId ";
		if(customerId != null) {
			queryString += " and c.customerId <> :customerId ";
		}

		TypedQuery<CustomerProfileEntity> query = entityManager.createQuery(queryString, CustomerProfileEntity.class)
		        .setParameter("userId", userId.toUpperCase());

		if (customerId != null) {
		    query.setParameter("customerId", customerId);
		}

		List<CustomerProfileEntity> result = query.getResultList();
		
		if(result!=null&&result.size()>0) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<CustomerProfileEntity> listAllCustomerDetailsByPage(Integer pageNo) {
		String queryString = "SELECT c FROM CustomerProfileEntity c WHERE status = :status order by customerId ASC ";

		Integer amtPage = 10;
		List<CustomerProfileEntity> result = entityManager.createQuery(queryString, CustomerProfileEntity.class)
				.setParameter("status",'A').setFirstResult((pageNo) * amtPage).setMaxResults(amtPage).getResultList();
		
		if(result!=null&&result.size()>0) {
			return result;
		} else {
			return null;
		}
	}
	
	public void saveCustomerProfileEntity(CustomerProfileEntity cp) {
		entityManager.persist(cp);
		entityManager.flush();
	}
	
	public void updateCustomerProfileEntity(CustomerProfileEntity cp) {
		entityManager.merge(cp);
		entityManager.flush();
	}
}
