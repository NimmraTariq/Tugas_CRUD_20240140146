package com.example.demo.repository;

import com.example.demo.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Integer> {

    Optional<Citizen> findByNik(String nik);

    boolean existsByNik(String nik);
}
