package com.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.model.Ticket;
@Repository
public interface ApsrtcReposiotry extends JpaRepository<Ticket, Long> {
	
	
	public Ticket findByName(String name);
	
	public Ticket findByNameAndCost(String name,Integer cost);
	

}
