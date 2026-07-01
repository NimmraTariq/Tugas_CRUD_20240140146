# Aplikasi CRUD Data Penduduk - 20240140146

## Deskripsi

Aplikasi CRUD (Create, Read, Update, Delete) untuk mengelola data penduduk menggunakan Spring Boot sebagai backend API dan MySQL sebagai database.
Frontend dibangun menggunakan HTML, CSS, JavaScript modern dengan Axios untuk berkomunikasi dengan API tanpa perlu refresh halaman.

Fitur utama:

- Menambah data penduduk
- Melihat semua data penduduk
- Mengubah data penduduk
- Menghapus data penduduk


## Database

Nama schema:
spring

Nama tabel:
citizen

Struktur tabel:

id (int, primary key, auto increment)
nik (varchar, unique)
nama (varchar)
alamat (varchar)
tgl_lahir (date)
jenis_kelamin (varchar)

SQL:

CREATE TABLE citizen (
id INT AUTO_INCREMENT PRIMARY KEY,
nik VARCHAR(50) UNIQUE,
nama VARCHAR(100),
alamat VARCHAR(255),
tgl_lahir DATE,
jenis_kelamin VARCHAR(20)
);


## Teknologi yang Digunakan

Backend:
- Java
- Spring Boot
- Spring Data JPA
- MySQL

Frontend:
- HTML5
- CSS3
- JavaScript (ES6+)
- Axios

Tools:
- IntelliJ IDEA
- MySQL Workbench
- Postman
- GitHub


## Struktur Package

model  
repository  
service  
service.impl  
controller  
dto  
mapper  

Keterangan:

model = representasi tabel database  
repository = akses data ke database  
service = logika bisnis  
impl = implementasi service  
controller = REST API endpoint  
dto = objek transfer data  
mapper = konversi antar entity dan dto  


## Endpoint API

POST /api/citizen  
Menambah data baru

GET /api/citizen  
Mendapatkan semua data

GET /api/citizen/{id}  
Mendapatkan data berdasarkan id

PUT /api/citizen/{id}  
Mengubah data

DELETE /api/citizen/{id}  
Menghapus data


Contoh JSON:

{
"nik": "3201234567890001",
"nama": "Nimra Tariq",
"alamat": "Jl. Sudirman No. 123",
"tglLahir": "2003-05-15",
"jenisKelamin": "P"
}


## Client Side

File frontend:

src/main/resources/static/index.html

Fitur:

- Form input data penduduk
- Tabel data dengan pagination
- Edit data langsung di modal
- Delete dengan konfirmasi
- Notifikasi feedback
- AJAX tanpa refresh

Buka di browser:

http://localhost:8080/


## Cara Menjalankan

1. Pastikan MySQL sudah berjalan
2. Buat database dengan nama:

spring

3. Jalankan project Spring Boot

4. Buka browser:

http://localhost:8080/


## Screenshot

Letakkan screenshot di folder:

screenshots/

Contoh:

screenshots/db.png  
screenshots/api.png  
screenshots/web.png  
screenshots/project.png


## Repository

Nama repository:

Tugas_CRUD_20240140146

Minimum commit: 10


## Status

Semua operasi CRUD berfungsi dengan baik pada API dan frontend.
