package com.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.model.Ticket;
import com.poc.service.ApsrtcService;

@RestController
public class ApsrtcController {

	@Autowired
	ApsrtcService apsrtcService;

	@PostMapping("/create-bus-ticket")
	public Ticket createTicket(@RequestBody Ticket ticket) {

		return apsrtcService.createTicket(ticket);

	}

	@PutMapping("/update-bus-ticket/{id}")
	public Ticket updateTicket(@RequestBody Ticket ticket, @PathVariable Long id) {

		return apsrtcService.updateTicket(ticket, id);

	}

	@GetMapping("/get-bus-ticket/{id}")
	public Ticket getTicketById(@PathVariable Long id) {

		return apsrtcService.getTicketById(id);

	}

	@GetMapping("/get-All-tickets")
	public List<Ticket> getAllTickets() {

		return apsrtcService.getAllTickets();

	}

	@DeleteMapping("/delete-bus-ticket/{id}")
	public void deleteTicketById(@PathVariable Long id) {

		apsrtcService.deleteTicketById(id);

	}

}
