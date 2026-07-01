package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "citizen")
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nik", unique = true, nullable = false, length = 50)
    private String nik;

    @Column(name = "nama", nullable = false, length = 100)
    private String nama;

    @Column(name = "alamat", length = 255)
    private String alamat;

    @Column(name = "tgl_lahir")
    private LocalDate tglLahir;

    @Column(name = "jenis_kelamin", length = 20)
    private String jenisKelamin;

    public Citizen() {
    }

    public Citizen(String nik, String nama, String alamat, LocalDate tglLahir, String jenisKelamin) {
        this.nik = nik;
        this.nama = nama;
        this.alamat = alamat;
        this.tglLahir = tglLahir;
        this.jenisKelamin = jenisKelamin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public LocalDate getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(LocalDate tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }
}
