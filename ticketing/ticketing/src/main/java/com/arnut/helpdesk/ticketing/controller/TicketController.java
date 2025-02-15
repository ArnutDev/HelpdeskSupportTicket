package com.arnut.helpdesk.ticketing.controller;

import com.arnut.helpdesk.ticketing.entity.Ticket;
import com.arnut.helpdesk.ticketing.services.TicketServiceAction;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tickets")
@CrossOrigin(origins = "*")
public class TicketController {
    @Autowired
    private TicketServiceAction ticketServiceAction;

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketServiceAction.getAllTickets();
    }

    @GetMapping("/status/{status}")
    public List<Ticket> getTicketsByStatus(@PathVariable Ticket.TicketStatus status) {
        return ticketServiceAction.getTicketsByStatus(status);
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<Ticket> acceptTicket(@PathVariable Integer id, @RequestBody Ticket ticketDetails) {
        Optional<Ticket> updatedTicket = ticketServiceAction.acceptTicket(id, ticketDetails);
        return updatedTicket.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/resolve/{id}")
    public ResponseEntity<Ticket> resolveTicket(@PathVariable Integer id, @RequestBody Ticket ticketDetails) {
        Optional<Ticket> updatedTicket = ticketServiceAction.resolveTicket(id, ticketDetails);
        return updatedTicket.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<Ticket> rejectTicket(@PathVariable Integer id, @RequestBody Ticket ticketDetails) {
        Optional<Ticket> updatedTicket = ticketServiceAction.rejectTicket(id, ticketDetails);
        return updatedTicket.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createTicket")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
        Ticket createdTicket = ticketServiceAction.createTicket(ticket);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }



}
