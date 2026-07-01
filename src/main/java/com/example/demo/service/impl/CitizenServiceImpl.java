package com.example.demo.service.impl;

import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.DuplicateNikException;
import com.example.demo.model.Citizen;
import com.example.demo.repository.CitizenRepository;
import com.example.demo.service.CitizenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CitizenServiceImpl implements CitizenService {

    private final CitizenRepository repo;

    public CitizenServiceImpl(CitizenRepository repo) {
        this.repo = repo;
    }

    @Override
    public Citizen simpanData(Citizen data) {
        if (repo.existsByNik(data.getNik())) {
            throw new DuplicateNikException("NIK sudah terdaftar dalam sistem");
        }
        return repo.save(data);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Citizen> ambilSemuaData() {
        return repo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Citizen ambilDataById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Data dengan ID " + id + " tidak ditemukan"));
    }

    @Override
    public Citizen perbaruiData(Integer id, Citizen dataBaru) {
        Citizen yangAda = repo.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Data dengan ID " + id + " tidak ditemukan"));

        if (!yangAda.getNik().equals(dataBaru.getNik()) && repo.existsByNik(dataBaru.getNik())) {
            throw new DuplicateNikException("NIK sudah terdaftar dalam sistem");
        }

        yangAda.setNik(dataBaru.getNik());
        yangAda.setNama(dataBaru.getNama());
        yangAda.setAlamat(dataBaru.getAlamat());
        yangAda.setTglLahir(dataBaru.getTglLahir());
        yangAda.setJenisKelamin(dataBaru.getJenisKelamin());

        return repo.save(yangAda);
    }

    @Override
    public void hapusData(Integer id) {
        if (!repo.existsById(id)) {
            throw new DataNotFoundException("Data dengan ID " + id + " tidak ditemukan");
        }
        repo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean cekNikSudahAda(String nik) {
        return repo.existsByNik(nik);
    }
}
