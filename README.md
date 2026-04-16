Student Management System - A Multi-Role Academic Portal (Admin, Teacher, Student)

Student Management System is a full-stack web application developed to handle school/college operations. The project focuses on the core "Enterprise Java" stack (JSP, Servlet, JDBC), demonstrating a deep understanding of manual data mapping, session management, and relational database integrity.

🛠️ Features Implemented

🔐 Security & Authentication

BCrypt Hashing: All user passwords are encrypted using the BCrypt algorithm. This ensures that even with database access, raw passwords remain protected via one-way salted hashes.

Role-Based Access Control (RBAC): A centralized login system that directs Admins, Teachers, and Students to their respective authorized dashboards.

Secure Password Updates: A dedicated module for users to update credentials, including logic to prevent reusing the current password.

👑 Administrator Module

Complete CRUD: Full control over Student and Teacher records (Create, Read, Update, and Delete).

Soft Delete Logic: Implemented a non-destructive "Soft Delete" system using is_deleted flags to maintain historical data integrity.

Academic Mapping: Tools to enroll Students into Courses and assign Teachers to specific subjects.

Advanced Search: A real-time search filter using complex SQL JOINs to find records across interconnected tables (Users, Students, and Enrollments).

👨‍🏫 Teacher Portal

Course Tracking: Teachers can view all courses they are currently leading.

Classroom Management: Access to lists of students enrolled in their specific courses.

Bulk Attendance & Grading: A streamlined interface allowing teachers to submit attendance and grades for an entire class in a single transaction.

🎓 Student Portal

Enrollment View: Students can see their active courses and assigned faculty.

Performance Monitoring: A dashboard to track attendance percentages and exam grades.

⚡ Interactive UI Features

AJAX Profile Editor: Implemented "Click-to-Edit" functionality using the JavaScript Fetch API. This allows users to update their Name or Email instantly without a page refresh.

Transactional Integrity: Manual management of SQL Transactions (setAutoCommit(false)) to ensure that updates across multiple tables (like users and students) either succeed together or fail safely.

💻 Technical Stack

Backend: Java (Servlets), JDBC (Java Database Connectivity).

Frontend: JSP (JavaServer Pages), HTML5, CSS3, JavaScript (AJAX/Fetch).

Database: MySQL (Relational Schema with Foreign Key Constraints).

Security: jBCrypt library for password salting and hashing.

Server: Apache Tomcat.

📂 Project Architecture

The project follows a clean DAO (Data Access Object) Pattern:

Model: Plain Old Java Objects (POJOs) representing database entities.

DAO: Separated business logic from SQL execution; handles all JDBC connections and ResultSets.

Controller (Servlets): Manages request routing, session handling, and data flow between the UI and the DAO.

View (JSP): Dynamic web pages that render data using Expression Language (EL) and Scriptlets.

🛡️ Repository Hygiene

Secure Configs: Utilizes a .gitignore and DBconnectionExample.java template to keep local database credentials private.

Clean History: Adheres to professional standards by excluding IDE-specific files (.settings, .classpath) from the repository.
