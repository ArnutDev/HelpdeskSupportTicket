package com.arnut.helpdesk.ticketing.services;

import com.arnut.helpdesk.ticketing.entity.Ticket;
import com.arnut.helpdesk.ticketing.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

public class TicketServiceActionTest {

    @InjectMocks
    private TicketServiceAction ticketServiceAction;

    @Mock
    private TicketRepository ticketRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ทดสอบการสร้าง ticket
    @Test
    void testCreateTicket() {
        Ticket ticket = new Ticket();
        ticket.setTitle("Issue with login");
        ticket.setDescription("User can't log in");
        ticket.setContactInformation("user@example.com");

        Ticket createdTicket = new Ticket(1, ticket.getTitle(), ticket.getDescription(), ticket.getContactInformation(),
                LocalDateTime.now(), LocalDateTime.now(), Ticket.TicketStatus.PENDING);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(createdTicket);

        Ticket result = ticketServiceAction.createTicket(ticket);

        assertNotNull(result.getId(), "Ticket ID should not be null");
        assertEquals(Ticket.TicketStatus.PENDING, result.getStatus(), "Status should be PENDING");
        assertEquals("Issue with login", result.getTitle(), "Title should be the same");
    }

    // ทดสอบการอัปเดตสถานะของ ticket เป็น ACCEPTED
    @Test
    void testAcceptTicket() {
        Ticket ticket = new Ticket();
        ticket.setTitle("Issue with login");
        ticket.setDescription("User can't log in");
        ticket.setContactInformation("user@example.com");
        Ticket createdTicket = new Ticket(1, ticket.getTitle(), ticket.getDescription(), ticket.getContactInformation(),
                LocalDateTime.now(), LocalDateTime.now(), Ticket.TicketStatus.PENDING);

        when(ticketRepository.findById(1)).thenReturn(Optional.of(createdTicket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(createdTicket);

        Optional<Ticket> result = ticketServiceAction.acceptTicket(1, createdTicket);

        assertTrue(result.isPresent(), "Ticket should be updated");
        assertEquals(Ticket.TicketStatus.ACCEPTED, result.get().getStatus(), "Status should be ACCEPTED");
    }

    // ทดสอบการอัปเดตสถานะของ ticket เป็น RESOLVED
    @Test
    void testResolveTicket() {
        Ticket ticket = new Ticket();
        ticket.setTitle("Issue with login");
        ticket.setDescription("User can't log in");
        ticket.setContactInformation("user@example.com");
        Ticket createdTicket = new Ticket(1, ticket.getTitle(), ticket.getDescription(), ticket.getContactInformation(),
                LocalDateTime.now(), LocalDateTime.now(), Ticket.TicketStatus.ACCEPTED);

        when(ticketRepository.findById(1)).thenReturn(Optional.of(createdTicket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(createdTicket);

        Optional<Ticket> result = ticketServiceAction.resolveTicket(1, createdTicket);

        assertTrue(result.isPresent(), "Ticket should be updated");
        assertEquals(Ticket.TicketStatus.RESOLVED, result.get().getStatus(), "Status should be RESOLVED");
    }

    // ทดสอบการอัปเดตสถานะของ ticket เป็น REJECTED
    @Test
    void testRejectTicket() {
        Ticket ticket = new Ticket();
        ticket.setTitle("Issue with login");
        ticket.setDescription("User can't log in");
        ticket.setContactInformation("user@example.com");
        Ticket createdTicket = new Ticket(1, ticket.getTitle(), ticket.getDescription(), ticket.getContactInformation(),
                LocalDateTime.now(), LocalDateTime.now(), Ticket.TicketStatus.PENDING);

        when(ticketRepository.findById(1)).thenReturn(Optional.of(createdTicket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(createdTicket);

        Optional<Ticket> result = ticketServiceAction.rejectTicket(1, createdTicket);

        assertTrue(result.isPresent(), "Ticket should be updated");
        assertEquals(Ticket.TicketStatus.REJECTED, result.get().getStatus(), "Status should be REJECTED");
    }
}
