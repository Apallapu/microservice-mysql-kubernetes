package com.poc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketId;
	private String name;

	private String ticket_from;
	private String ticket_to;
	private Integer cost;

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTicket_from() {
		return ticket_from;
	}

	public void setTicket_from(String ticket_from) {
		this.ticket_from = ticket_from;
	}

	public String getTicket_to() {
		return ticket_to;
	}

	public void setTicket_to(String ticket_to) {
		this.ticket_to = ticket_to;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

}
