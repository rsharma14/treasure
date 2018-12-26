package com.salesstock.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.salesstock.entity.BillInfo;

@Repository
public class HomeDaoImpl implements HomeDao {
	
	@PersistenceContext
	EntityManager entityManager;
	
@Override
public List<BillInfo> shopBillPerson(HttpServletRequest request, HttpSession session) throws Exception {
	
	CriteriaBuilder cb=entityManager.getCriteriaBuilder();
	CriteriaQuery<Object> cq=cb.createQuery();
	Root<BillInfo> root= cq.from(BillInfo.class);
	cq.select(root);
	
	List<Predicate> pl=new ArrayList<>();

	//pl.add(cb.equal(root.get("sessionId"),session.getId()));
	cq.orderBy(cb.asc(root.get("billInfoCK")));
	cq.where(pl.toArray(new Predicate[0]));
	
	 Query query1=entityManager.createQuery(cq);
	 
	return query1.getResultList();
}
}
