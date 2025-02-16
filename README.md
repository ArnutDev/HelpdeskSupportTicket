# ระบบจัดการตั๋วช่วยเหลือ (Helpdesk Support Ticket Management)

ระบบนี้เป็นระบบจัดการตั๋วช่วยเหลือที่ผู้ใช้สามารถสร้างตั๋วได้และดูประวัติของตั๋วที่สร้างขึ้น ส่วนผู้ดูแลสามารถดูรายการคำขอและจัดการตั๋วได้

## ฟีเจอร์

### ผู้ใช้ (User)
- **สร้างตั๋ว**: ผู้ใช้สามารถสร้างตั๋วช่วยเหลือใหม่ได้
- **ดูประวัติของตั๋ว**: ผู้ใช้สามารถดูประวัติของตั๋วที่สร้างไว้ ผ่านตัวกรองโดยสถานะ

### ผู้ดูแล (Supporter)
- **ดูรายการคำขอ**: ผู้ดูแลสามารถดูรายการคำขอของตั๋วทั้งหมด
- **จัดการตั๋ว**: ผู้ดูแลสามารถจัดการและอัปเดตสถานะของตั๋วได้

## เทคโนโลยีที่ใช้

- **Frontend**: Vue.js
- **Backend**: Java Spring Boot
- **Database**: MySQL
- **IDE**: Visual Studio Code (VSCode)

# การติดตั้ง Node.js, npm และ XAMPP

## 1. ติดตั้ง Node.js และ npm
1. ดาวน์โหลด **Node.js** (รวม **npm**) จาก [https://nodejs.org](https://nodejs.org)
2. เลือกเวอร์ชัน **LTS** (Long Term Support) และทำการติดตั้ง
3. ตรวจสอบการติดตั้ง:
   ```bash
   node -v
   npm -v
## 2. ติดตั้งและตั้งค่า XAMPP 
1. ดาวน์โหลด XAMPP จาก https://www.apachefriends.org/index.html
2. เลือกระบบปฏิบัติการที่ใช้ (Windows, macOS, Linux) และทำการติดตั้ง
3. เปิด XAMPP Control Panel และเริ่ม Apache และ MySQL
4. ตรวจสอบการทำงานโดยไปที่ http://localhost
5. ไปที่ http://localhost/phpmyadmin/ และสร้าง database ขึ้นมาใหม่ ชื่อว่า ticket_db

## การตั้งค่า

เริ่มต้นโคลน repository นี้

### การตั้งค่าฝั่ง Backend

1. เปิดIDE ที่มี repository นี้ ไปที่ไดเรกทอรี ticketing\ticketing\src\main\resources\application.properties
2. แก้ไขข้อมูลการเชื่อมต่อ database ของตนเอง ส่วนใหญ่แก้แค่ 3 บรรทัดนี้ (localhost ดูได้จาก XAMMP port ของ MySQL)
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3307/ticket_db
   spring.datasource.username=root
   spring.datasource.password=
3. เปิด Terminal cd ticketing/ticketing
4. พิมพ์คำสั่ง mvnw spring-boot:run เพื่อเริ่มรัน backend

### การตั้งค่าฝั่ง Frontend

1. เปิด Terminal อีกอัน cd ticketing/ticket-app
2. พิมพ์คำสั่ง npm run serve เพื่อเริ่มรัน frontend
3. ภายใน Terminal จะเห็น
   ```bash
   App running at:
   - Local:   http://localhost:8081/ 
   - Network: http://192.168.0.104:8081/
 
   Note that the development build is not optimized.
   To create a production build, run npm run build.
4. คลิกลิ้งค์ Local หรือ  Network ก็ได้ เพื่อแสดงหน้าเว็บที่พร้อมใช้งาน

### การใช้งาน
- เลือก Login as user เพื่อเข้าใช้งาน สร้าง ticket
- เลือก Login as supporter เพื่อเข้าใช้งาน จัดการคำขอ ticket
