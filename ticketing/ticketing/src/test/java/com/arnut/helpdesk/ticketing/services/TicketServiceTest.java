package com.arnut.helpdesk.ticketing.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.arnut.helpdesk.ticketing.model.Ticket;
import com.arnut.helpdesk.ticketing.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    public void testGetAllTickets() {
        List<Ticket> mockTickets = Arrays.asList(
                new Ticket(1, "Issue 1", "Description 1", "user1@email.com"),
                new Ticket(2, "Issue 2", "Description 2", "user2@email.com")
        );

        when(ticketRepository.findAll()).thenReturn(mockTickets);

        List<Ticket> result = ticketService.getAllTickets();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getTitle()).isEqualTo("Issue 1");
    }
}
