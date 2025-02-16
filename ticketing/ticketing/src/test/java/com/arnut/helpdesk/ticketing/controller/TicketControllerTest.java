package com.arnut.helpdesk.ticketing.controller;

import com.arnut.helpdesk.ticketing.entity.Ticket;
import com.arnut.helpdesk.ticketing.services.TicketServiceAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TicketControllerTest {

    @InjectMocks
    private TicketController ticketController;

    @Mock
    private TicketServiceAction ticketServiceAction;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
    }

    @Test
    void testCreateTicket() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setTitle("Issue with login");
        ticket.setDescription("User can't log in");
        ticket.setContactInformation("user@example.com");

        when(ticketServiceAction.createTicket(any(Ticket.class))).thenReturn(ticket);

        mockMvc.perform(post("/api/tickets/createTicket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Issue with login\", \"description\": \"User can't log in\", \"contactInformation\": \"user@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Issue with login"))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    void testGetAllTickets() throws Exception {
        mockMvc.perform(get("/api/tickets"))
                .andExpect(status().isOk());
    }

    @Test
    void testAcceptTicket() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setTitle("Issue with login");
        ticket.setDescription("User can't log in");
        ticket.setContactInformation("user@example.com");

        when(ticketServiceAction.acceptTicket(1, ticket)).thenReturn(Optional.of(ticket));

        mockMvc.perform(put("/api/tickets/accept/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Issue with login\", \"description\": \"User can't log in\", \"contactInformation\": \"user@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACCEPTED"));
    }
}
