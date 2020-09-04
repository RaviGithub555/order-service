package com.order.system.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.order.system.model.Customer;

@Repository
//public interface CustomerRepository extends JpaRepository<Customer, Long> {
public class CustomerRepository{

	@Autowired
	MongoOperations mongoOperations;

	public Customer save(@Valid Customer customer) {
		Customer custmr = null;
		try {
			mongoOperations.save(customer, "customers");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return custmr;
		
	}
	
	public Customer fetchCustomerById(@Valid Long id) {
		Customer custmr = null;
		try {
			Query query = new Query(Criteria.where("CUSTOMER_ID").is(id));
			custmr = mongoOperations.findOne(query, Customer.class, "customers");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return custmr;
	}

	public List<Customer> findAll() {
		List<Customer> custList = null;
		try {
			custList = mongoOperations.findAll(Customer.class, "customers");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return custList;
	}

}
