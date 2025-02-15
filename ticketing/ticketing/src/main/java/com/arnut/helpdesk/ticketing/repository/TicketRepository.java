package com.arnut.helpdesk.ticketing.repository;

import com.arnut.helpdesk.ticketing.entity.Ticket;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByStatus(Ticket.TicketStatus status, Sort sort);
    List<Ticket> findAllByOrderByUpdatedTimestampDesc();
}


