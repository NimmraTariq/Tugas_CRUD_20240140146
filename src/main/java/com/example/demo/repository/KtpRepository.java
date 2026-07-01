package com.example.demo.repository;

import com.example.demo.entity.Ktp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KtpRepository extends JpaRepository<Ktp, Integer> {

    Optional<Ktp> findByNomorKtp(String nomorKtp);

}