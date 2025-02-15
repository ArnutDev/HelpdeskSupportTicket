package com.arnut.helpdesk.ticketing.services;


import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    // ตัวอย่างการตรวจสอบผู้ใช้ (Hardcoded หรือใช้ฐานข้อมูลจริง)
    public boolean authenticate(String username, String password) {
        // ตัวอย่าง hardcoded สำหรับ username "admin" และ password "password"
        return "admin".equals(username) && "password".equals(password);
    }
}

