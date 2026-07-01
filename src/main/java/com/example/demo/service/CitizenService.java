package com.example.demo.service;

import com.example.demo.model.Citizen;
import java.util.List;

public interface CitizenService {

    Citizen simpanData(Citizen data);

    List<Citizen> ambilSemuaData();

    Citizen ambilDataById(Integer id);

    Citizen perbaruiData(Integer id, Citizen data);

    void hapusData(Integer id);

    boolean cekNikSudahAda(String nik);
}
