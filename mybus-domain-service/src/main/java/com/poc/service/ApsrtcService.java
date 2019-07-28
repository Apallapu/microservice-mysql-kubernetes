package com.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.poc.exception.TicketIdNotFoundException;
import com.poc.exception.TicketUpdateException;
import com.poc.model.Ticket;
import com.poc.repository.ApsrtcReposiotry;

@Service
public class ApsrtcService {

	@Autowired
	ApsrtcReposiotry apsrtcReposiotry;

	public Ticket createTicket(Ticket ticket) {
		return apsrtcReposiotry.save(ticket);
	}

	public Ticket updateTicket(Ticket ticket, Long id) {
		Ticket updatedTicket = apsrtcReposiotry.findById(id).orElseThrow(()->new TicketUpdateException(HttpStatus.NOT_FOUND.value(),"Ticket Id Not Found for update the record"));
		updatedTicket.setCost(ticket.getCost());
		updatedTicket.setName(ticket.getName());
		updatedTicket.setTicket_from(ticket.getTicket_from());
		updatedTicket.setTicket_to(ticket.getTicket_to());

		return apsrtcReposiotry.save(updatedTicket);
	}

	public Ticket getTicketById(Long id) {
		return apsrtcReposiotry.findById(id).orElseThrow(()->new TicketIdNotFoundException(HttpStatus.NOT_FOUND.value(),"Ticket Id Not Found"));
	}

	public List<Ticket> getAllTickets() {

		return apsrtcReposiotry.findAll();
	}

	public void deleteTicketById(Long id) {
		//apsrtcReposiotry.deleteById(id);
		apsrtcReposiotry.deleteById(id);
	}

}
