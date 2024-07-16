Project Title: File Encryptor
Table of Contents
Introduction
Features
Technologies Used
Setup and Installation
Usage

Introduction
File Encryptor is a Java-based application designed to secure your files by hiding their paths and providing a unique ID for each file. The application ensures secure user authentication through email verification with a one-time password (OTP) before granting access to the file encryption functionalities.

Features
User registration and login with email verification.
OTP confirmation sent to user email for secure login.
Hide file paths to secure files.
Unhide file paths using unique IDs assigned to files.
Database integration using MySQL for user and file management.

Technologies Used
Java
MySQL
Clone the repository:

bash
Copy code
git clone https://github.com/yourusername/file-encryptor.git
cd file-encryptor

Set up the database:

Install MySQL and create a database named file_encryptor.
Execute the SQL script provided in the db directory to create necessary tables.
Configure database connection:

Update the database connection settings in the dbconfig.properties file.

Build the project:

Open the project in your preferred IDE (e.g., Eclipse, IntelliJ IDEA).
Build the project to download all dependencies.
Deploy the project:

Deploy the project on a web server (e.g., Apache Tomcat).
Usage
User Registration:

Open the application in a web browser.
Register with your email and create a password.
An OTP will be sent to your email for confirmation.
Enter the OTP to verify and complete the registration process.
User Login:

Log in with your registered email and password.
An OTP will be sent to your email for secure login.
Enter the OTP to access the application.

Hide File Path:

After logging in, navigate to the file encryption section.
Provide the file path you want to hide.
A unique ID will be generated and assigned to the file.

Unhide File Path:

Use the unique ID to retrieve the hidden file path.
Database Schema
The database contains the following tables:

users:

id (Primary Key)
email (Unique)
password
otp
files:

id (Primary Key)
user_id (Foreign Key referencing users)
file_path
unique_id

