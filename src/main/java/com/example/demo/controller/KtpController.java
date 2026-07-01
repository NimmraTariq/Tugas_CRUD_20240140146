package com.example.demo.controller;

import com.example.demo.entity.Ktp;
import com.example.demo.service.KtpService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/ktp")
public class KtpController {

    private final KtpService service;

    public KtpController(KtpService service) {
        this.service = service;
    }

    @PostMapping
    public Ktp create(@RequestBody Ktp ktp) {
        return service.create(ktp);
    }

    @GetMapping
    public List<Ktp> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Ktp getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Ktp update(@PathVariable Integer id,
                      @RequestBody Ktp ktp) {
        return service.update(id, ktp);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}