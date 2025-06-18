# 📊 Smart Clinic Management System - MySQL Database Schema

## 🏥 Entity Relationship Design

The Smart Clinic system uses a relational database designed for efficiency, scalability, and clarity in medical record and appointment management.

---

## 👤 Doctor Table

| Field Name | Data Type    | Constraints                 |
|------------|--------------|-----------------------------|
| id         | BIGINT       | PRIMARY KEY, AUTO_INCREMENT |
| name       | VARCHAR(255) | NOT NULL                    |
| email      | VARCHAR(255) | UNIQUE, NOT NULL            |
| speciality | VARCHAR(255) | NOT NULL                    |
| phone      | VARCHAR(15)  |                             |

---

## 🧑 Patient Table

| Field Name | Data Type    | Constraints                 |
|------------|--------------|-----------------------------|
| id         | BIGINT       | PRIMARY KEY, AUTO_INCREMENT |
| name       | VARCHAR(255) | NOT NULL                    |
| email      | VARCHAR(255) | UNIQUE                      |
| phone      | VARCHAR(15)  |                             |
| birth_date | DATE         |                             |

---

## 📅 Appointment Table

| Field Name       | Data Type | Constraints                       |
|------------------|-----------|-----------------------------------|
| id               | BIGINT    | PRIMARY KEY, AUTO_INCREMENT       |
| doctor_id        | BIGINT    | FOREIGN KEY → Doctor(id)          |
| patient_id       | BIGINT    | FOREIGN KEY → Patient(id)         |
| appointment_time | DATETIME  | NOT NULL                          |
| notes            | TEXT      |                                   |

---

## 💊 Prescription Table

| Field Name     | Data Type    | Constraints                       |
|----------------|--------------|-----------------------------------|
| id             | BIGINT       | PRIMARY KEY, AUTO_INCREMENT       |
| appointment_id | BIGINT       | FOREIGN KEY → Appointment(id)     |
| medication     | VARCHAR(255) | NOT NULL                          |
| dosage         | VARCHAR(255) | NOT NULL                          |
| instructions   | TEXT         |                                   |

---

## 🔐 User Table (Login and Role-based Access)

| Field Name | Data Type    | Constraints                                 |
|------------|--------------|---------------------------------------------|
| id         | BIGINT       | PRIMARY KEY, AUTO_INCREMENT                 |
| username   | VARCHAR(255) | UNIQUE, NOT NULL                            |
| password   | VARCHAR(255) | NOT NULL                                    |
| role       | ENUM         | CHECK (role IN ('ADMIN','DOCTOR','PATIENT'))|
| linked_id  | BIGINT       | References Doctor or Patient based on role  |

---

## 🧠 Stored Procedures

### 📌 GetDailyAppointmentReportByDoctor

Returns the list of patients for a specific doctor on a specific date.

```sql
CALL GetDailyAppointmentReportByDoctor(doctorId, '2025-06-18');
