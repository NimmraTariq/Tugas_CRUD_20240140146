package com.example.demo;

import com.example.demo.model.Citizen;
import com.example.demo.repository.CitizenRepository;
import com.example.demo.service.CitizenService;
import com.example.demo.service.impl.CitizenServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CitizenServiceTest {

    @Mock
    private CitizenRepository repository;

    @InjectMocks
    private CitizenServiceImpl service;

    private Citizen sampleCitizen;

    @BeforeEach
    void setUp() {
        sampleCitizen = new Citizen();
        sampleCitizen.setId(1);
        sampleCitizen.setNik("3201234567890001");
        sampleCitizen.setNama("Nimra Tariq");
        sampleCitizen.setAlamat("Jl. Sudirman No. 123");
        sampleCitizen.setTglLahir(LocalDate.of(2003, 5, 15));
        sampleCitizen.setJenisKelamin("P");
    }

    @Test
    void testSimpanData_Success() {
        when(repository.existsByNik(anyString())).thenReturn(false);
        when(repository.save(any(Citizen.class))).thenReturn(sampleCitizen);

        Citizen result = service.simpanData(sampleCitizen);

        assertNotNull(result);
        assertEquals("Nimra Tariq", result.getNama());
        assertEquals("3201234567890001", result.getNik());
        verify(repository, times(1)).save(any(Citizen.class));
    }

    @Test
    void testAmbilSemuaData() {
        Citizen citizen2 = new Citizen();
        citizen2.setId(2);
        citizen2.setNik("3201234567890002");
        citizen2.setNama("John Doe");

        when(repository.findAll()).thenReturn(Arrays.asList(sampleCitizen, citizen2));

        List<Citizen> result = service.ambilSemuaData();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testAmbilDataById_Found() {
        when(repository.findById(1)).thenReturn(Optional.of(sampleCitizen));

        Citizen result = service.ambilDataById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Nimra Tariq", result.getNama());
    }

    @Test
    void testAmbilDataById_NotFound() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(com.example.demo.exception.DataNotFoundException.class,
            () -> service.ambilDataById(99));
    }

    @Test
    void testPerbaruiData_Success() {
        Citizen updatedData = new Citizen();
        updatedData.setNik("3201234567890001");
        updatedData.setNama("Nimra Tariq Updated");
        updatedData.setAlamat("Jl. Gatot Subroto");
        updatedData.setTglLahir(LocalDate.of(2003, 6, 20));
        updatedData.setJenisKelamin("P");

        when(repository.findById(1)).thenReturn(Optional.of(sampleCitizen));
        when(repository.save(any(Citizen.class))).thenReturn(updatedData);

        Citizen result = service.perbaruiData(1, updatedData);

        assertNotNull(result);
        assertEquals("Nimra Tariq Updated", result.getNama());
        assertEquals("Jl. Gatot Subroto", result.getAlamat());
        verify(repository, times(1)).save(any(Citizen.class));
    }

    @Test
    void testHapusData_Success() {
        when(repository.existsById(1)).thenReturn(true);
        doNothing().when(repository).deleteById(1);

        assertDoesNotThrow(() -> service.hapusData(1));
        verify(repository, times(1)).deleteById(1);
    }

    @Test
    void testHapusData_NotFound() {
        when(repository.existsById(99)).thenReturn(false);

        assertThrows(com.example.demo.exception.DataNotFoundException.class,
            () -> service.hapusData(99));
    }

    @Test
    void testCekNikSudahAda_True() {
        when(repository.existsByNik("3201234567890001")).thenReturn(true);

        assertTrue(service.cekNikSudahAda("3201234567890001"));
    }

    @Test
    void testCekNikSudahAda_False() {
        when(repository.existsByNik("9999999999999999")).thenReturn(false);

        assertFalse(service.cekNikSudahAda("9999999999999999"));
    }
}
