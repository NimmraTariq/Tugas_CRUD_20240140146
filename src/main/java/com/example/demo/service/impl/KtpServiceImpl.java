package com.example.demo.service.impl;

import com.example.demo.entity.Ktp;
import com.example.demo.repository.KtpRepository;
import com.example.demo.service.KtpService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KtpServiceImpl implements KtpService {

    private final KtpRepository repository;

    public KtpServiceImpl(KtpRepository repository) {
        this.repository = repository;
    }

    @Override
    public Ktp create(Ktp ktp) {

        repository.findByNomorKtp(ktp.getNomorKtp())
                .ifPresent(k -> {
                    throw new RuntimeException("Nomor KTP already exists");
                });

        return repository.save(ktp);
    }

    @Override
    public List<Ktp> getAll() {
        return repository.findAll();
    }

    @Override
    public Ktp getById(Integer id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Data not found"));
    }

    @Override
    public Ktp update(Integer id, Ktp ktp) {

        Ktp existing = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Data not found"));

        existing.setNomorKtp(ktp.getNomorKtp());
        existing.setNamaLengkap(ktp.getNamaLengkap());
        existing.setAlamat(ktp.getAlamat());
        existing.setTanggalLahir(ktp.getTanggalLahir());
        existing.setJenisKelamin(ktp.getJenisKelamin());

        return repository.save(existing);
    }

    @Override
    public void delete(Integer id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Data not found");
        }

        repository.deleteById(id);
    }
}