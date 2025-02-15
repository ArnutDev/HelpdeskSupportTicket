package com.arnut.helpdesk.ticketing.services;

import com.arnut.helpdesk.ticketing.entity.Ticket;
import com.arnut.helpdesk.ticketing.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceAction {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket){
        ticket.setCreatedTimestamp(LocalDateTime.now());
        ticket.setUpdatedTimestamp(LocalDateTime.now());
        ticket.setStatus(Ticket.TicketStatus.PENDING);
        return ticketRepository.save(ticket);
    }

    public Optional<Ticket> updateTicket(Integer id, Ticket ticketDetails){
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        if(ticketOptional.isPresent()){
            Ticket ticket = ticketOptional.get();
            ticket.setUpdatedTimestamp(LocalDateTime.now());
            System.out.println("title: "+ticket.getTitle()+" description: "+ticket.getDescription()+" contact: "+ticket.getContactInformation());
            ticket.setStatus(Ticket.TicketStatus.ACCEPTED);
//            System.out.println(ticket.getStatus()+" "+ticket.getTitle()+"sadasdada "+ticket.getContactInformation()+" "+ticket.getDescription());//?
            return Optional.of(ticketRepository.save(ticket));//บันทึกลง db
        }
        return Optional.empty();
    }

    public List<Ticket> getTicketsByStatus(Ticket.TicketStatus status){
        return ticketRepository.findByStatus(status);
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAllByOrderByUpdatedTimestampDesc();
    }
}
