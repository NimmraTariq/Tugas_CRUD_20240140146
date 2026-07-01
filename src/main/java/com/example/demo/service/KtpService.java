package com.example.demo.service;

import com.example.demo.entity.Ktp;

import java.util.List;

public interface KtpService {

    Ktp create(Ktp ktp);

    List<Ktp> getAll();

    Ktp getById(Integer id);

    Ktp update(Integer id, Ktp ktp);

    void delete(Integer id);

}