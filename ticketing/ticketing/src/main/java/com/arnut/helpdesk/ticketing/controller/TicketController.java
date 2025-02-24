package com.arnut.helpdesk.ticketing.controller;

import com.arnut.helpdesk.ticketing.entity.Ticket;
import com.arnut.helpdesk.ticketing.entity.Ticket.TicketStatus;
import com.arnut.helpdesk.ticketing.services.TicketServiceAction;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // คลาสนี้เป็น Controller สำหรับ RESTful API ให้ข้อมูลแบบ JSON
@RequestMapping("api/tickets") // ระบุ url หลัก
@CrossOrigin(origins = "*") // อนุญาตให้เข้าถึง API จากทุกโดเมน
public class TicketController {
    @Autowired // ผูกไว้ใช้ methodในคลาสนี้ได้
    private TicketServiceAction ticketServiceAction;

    @GetMapping
    public List<Ticket> getAllTickets(@RequestParam(required = false) String status) {
        if ("REJECTED".equals(status)) {
            return ticketServiceAction.getTicketsByStatus(TicketStatus.REJECTED);
        } else if ("RESOLVED".equals(status)) {
            return ticketServiceAction.getTicketsByStatus(TicketStatus.RESOLVED);
        } else if ("PENDING".equals(status)) {
            return ticketServiceAction.getTicketsByStatus(TicketStatus.PENDING);
        } else if ("ACCEPTED".equals(status)) {
            return ticketServiceAction.getTicketsByStatus(TicketStatus.ACCEPTED);
        }
        return ticketServiceAction.getAllTickets();
    }

    // @GetMapping("/status/{status}")
    // public List<Ticket> getTicketsByStatus(@PathVariable Ticket.TicketStatus
    // status) {// รับพารามิเตอร์สถานะของตั๋วจาก
    // // URL
    // return ticketServiceAction.getTicketsByStatus(status);
    // }

    @PutMapping("/accept/{id}") // รับค่า JSON จาก HTTP Request และแปลงเป็น Java Object
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
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket createdTicket = ticketServiceAction.createTicket(ticket);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

}
