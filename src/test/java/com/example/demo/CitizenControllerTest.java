package com.example.demo;

import com.example.demo.controller.CitizenController;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.GlobalExceptionHandler;
import com.example.demo.model.Citizen;
import com.example.demo.service.CitizenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CitizenControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CitizenService service;

    @InjectMocks
    private CitizenController controller;

    private ObjectMapper objectMapper;
    private Citizen sampleCitizen;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        sampleCitizen = new Citizen();
        sampleCitizen.setId(1);
        sampleCitizen.setNik("3201234567890001");
        sampleCitizen.setNama("Nimra Tariq");
        sampleCitizen.setAlamat("Jl. Sudirman No. 123");
        sampleCitizen.setTglLahir(LocalDate.of(2003, 5, 15));
        sampleCitizen.setJenisKelamin("P");
    }

    @Test
    void testGetAll_Success() throws Exception {
        List<Citizen> citizens = Arrays.asList(sampleCitizen);
        when(service.ambilSemuaData()).thenReturn(citizens);

        mockMvc.perform(get("/api/citizen"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].nama").value("Nimra Tariq"));

        verify(service, times(1)).ambilSemuaData();
    }

    @Test
    void testGetById_Success() throws Exception {
        when(service.ambilDataById(1)).thenReturn(sampleCitizen);

        mockMvc.perform(get("/api/citizen/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.nama").value("Nimra Tariq"));

        verify(service, times(1)).ambilDataById(1);
    }

    @Test
    void testGetById_NotFound() throws Exception {
        when(service.ambilDataById(99)).thenThrow(new DataNotFoundException("Data tidak ditemukan"));

        mockMvc.perform(get("/api/citizen/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Data tidak ditemukan"));

        verify(service, times(1)).ambilDataById(99);
    }

    @Test
    void testCreate_Success() throws Exception {
        when(service.simpanData(any(Citizen.class))).thenReturn(sampleCitizen);

        mockMvc.perform(post("/api/citizen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleCitizen)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.nama").value("Nimra Tariq"));

        verify(service, times(1)).simpanData(any(Citizen.class));
    }

    @Test
    void testUpdate_Success() throws Exception {
        Citizen updated = new Citizen();
        updated.setNik("3201234567890001");
        updated.setNama("Nimra Tariq Updated");
        updated.setAlamat("Jl. Gatot Subroto");
        updated.setTglLahir(LocalDate.of(2003, 6, 20));
        updated.setJenisKelamin("P");

        when(service.perbaruiData(eq(1), any(Citizen.class))).thenReturn(updated);

        mockMvc.perform(put("/api/citizen/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.nama").value("Nimra Tariq Updated"));

        verify(service, times(1)).perbaruiData(eq(1), any(Citizen.class));
    }

    @Test
    void testDelete_Success() throws Exception {
        doNothing().when(service).hapusData(1);

        mockMvc.perform(delete("/api/citizen/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Data berhasil dihapus"));

        verify(service, times(1)).hapusData(1);
    }
}
