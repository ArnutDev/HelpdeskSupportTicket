package com.arnut.helpdesk.ticketing.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String contactInformation;
    private LocalDateTime createdTimestamp;
    private LocalDateTime updatedTimestamp;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    public enum TicketStatus{
        PENDING,//รอดำเนินการ
        ACCEPTED,//ได้รับแล้ว
        RESOLVED,//แก้ไขแล้ว
        REJECTED//ถูกปฏิเสธ
    }

    public Ticket(){

    }
    public Ticket(int id, String title, String description, String contactInformation, LocalDateTime createdTimestamp, LocalDateTime updatedTimestamp, TicketStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.contactInformation = contactInformation;
        this.createdTimestamp = createdTimestamp;
        this.updatedTimestamp = updatedTimestamp;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public LocalDateTime getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUpdatedTimestamp(LocalDateTime updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    //test LocalDateTime
//    public static void main(String[] args) {
//        Ticket t1 = new Ticket();
//        t1.setCreatedTimestamp(LocalDateTime.now());
//        System.out.println(t1.getCreatedTimestamp());
//        t1.setUpdatedTimestamp(LocalDateTime.now());
//        System.out.println(t1.getUpdatedTimestamp());
//    }
}


