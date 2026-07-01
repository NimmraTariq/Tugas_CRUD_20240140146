package com.example.demo.controller;

import com.example.demo.model.Citizen;
import com.example.demo.service.CitizenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citizen")
@CrossOrigin(origins = "*")
public class CitizenController {

    private final CitizenService service;

    public CitizenController(CitizenService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Citizen>> tambahData(@RequestBody Citizen data) {
        Citizen tersimpan = service.simpanData(data);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Data berhasil disimpan", tersimpan));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Citizen>>> ambilSemuaData() {
        List<Citizen> daftar = service.ambilSemuaData();
        return ResponseEntity.ok(new ApiResponse<>(true, "Berhasil mengambil data", daftar));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Citizen>> ambilDataById(@PathVariable Integer id) {
        Citizen data = service.ambilDataById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Data ditemukan", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Citizen>> perbaruiData(
            @PathVariable Integer id,
            @RequestBody Citizen data) {
        Citizen diperbarui = service.perbaruiData(id, data);
        return ResponseEntity.ok(new ApiResponse<>(true, "Data berhasil diperbarui", diperbarui));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> hapusData(@PathVariable Integer id) {
        service.hapusData(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Data berhasil dihapus", null));
    }

    public static class ApiResponse<T> {
        private boolean success;
        private String message;
        private T data;

        public ApiResponse(boolean success, String message, T data) {
            this.success = success;
            this.message = message;
            this.data = data;
        }

        public boolean isSuccess() { return success; }
        public void setSuccess(boolean success) { this.success = success; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public T getData() { return data; }
        public void setData(T data) { this.data = data; }
    }
}
