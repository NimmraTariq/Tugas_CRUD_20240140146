# Tugas CRUD KTP - 20240140146

## Description

This project is a CRUD (Create, Read, Update, Delete) application for KTP data using Spring Boot as the server-side API and MySQL as the database.  
The client-side is built using HTML, CSS, JavaScript, and Ajax to interact with the API without refreshing the page.

The application supports:

- Add KTP data
- View KTP data
- Update KTP data
- Delete KTP data


## Database

Schema name:
spring

Table name:
ktp

Table structure:

id (int, primary key, auto increment)
nomor_ktp (varchar, unique)
nama_lengkap (varchar)
alamat (varchar)
tanggal_lahir (date)
jenis_kelamin (varchar)

SQL:

CREATE TABLE ktp (
id INT AUTO_INCREMENT PRIMARY KEY,
nomor_ktp VARCHAR(50) UNIQUE,
nama_lengkap VARCHAR(100),
alamat VARCHAR(255),
tanggal_lahir DATE,
jenis_kelamin VARCHAR(20)
);


## Technologies Used

Server side:
- Java
- Spring Boot
- Spring Data JPA
- MySQL

Client side:
- HTML
- CSS
- JavaScript
- Ajax / Fetch API

Tools:
- IntelliJ IDEA
- MySQL Workbench
- Postman
- GitHub


## Package Structure

entity  
repository  
service  
service.impl  
controller  
dto  
mapper  
util


Description:

entity = database model  
repository = database access  
service = business logic  
impl = service implementation  
controller = REST API  
dto = data transfer object  
mapper = convert entity to dto  
util = helper classes


## API Endpoints

POST /ktp  
Create new data

GET /ktp  
Get all data

GET /ktp/{id}  
Get data by id

PUT /ktp/{id}  
Update data

DELETE /ktp/{id}  
Delete data


Example JSON:

{
"nomorKtp": "1234",
"namaLengkap": "Saz",
"alamat": "Dhaka",
"tanggalLahir": "2000-01-01",
"jenisKelamin": "L"
}


## Client Side

Frontend file:

src/main/resources/static/index.html

Features:

- Add data
- Edit data
- Delete data
- Show table
- Ajax without refresh

Open in browser:

http://localhost:8080/index.html


## How to Run

1. Start MySQL
2. Create database named:

spring

3. Run Spring Boot project

4. Open browser:

http://localhost:8080/index.html


## Screenshots

Put screenshots in folder:

screenshots/

Example:

<img width="1181" height="1033" alt="db" src="https://github.com/user-attachments/assets/73567159-8316-48c8-b75d-f46be3966d2a" />

<img width="1652" height="998" alt="api" src="https://github.com/user-attachments/assets/b89515ac-3d09-4a2c-9e56-6a3068af5dcd" />

<img width="1919" height="1034" alt="web" src="https://github.com/user-attachments/assets/4e4f99c7-a770-4877-8eae-492afc83e687" />

<img width="1254" height="969" alt="project" src="https://github.com/user-attachments/assets/c99b78f8-fd4d-43fc-b980-b1efb0d71d9a" />



## Repository

Repository name:

Tugas_CRUD_20240140146
Minimum commits: 10


## Status

All CRUD operations work correctly on API and client-side.
