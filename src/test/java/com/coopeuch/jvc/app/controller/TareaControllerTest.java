package com.coopeuch.jvc.app.controller;

import com.coopeuch.jvc.app.entity.Tarea;
import com.coopeuch.jvc.app.repository.TareaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.mockito.Matchers.isA;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TareaControllerTest {

    @Mock
    private TareaRepository tareaRepositorio;

    @InjectMocks
    private TareaController tareaController;

    private Tarea tarea;
    private ResponseEntity<Tarea> deleted;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tarea = new Tarea();
        tarea.setId(1L);
        tarea.setDescripcion("Tarea Testing");
        tarea.setFechaCreacion(new Date());
        tarea.setVigente(false);
    }

    @Test
    void getAllTareas() {
        when(tareaController.getAllTareas()).thenReturn(Arrays.asList(tarea));
        assertNotNull(tareaController.getAllTareas());
    }

    @Test
    void crearTarea() {
        when(tareaController.crearTarea(any(Tarea.class))).thenReturn(tarea);
        assertNotNull(tareaController.crearTarea(new Tarea()));
    }

    @Test
    void obtenerTareaPorId() {

    }

    @Test
    void actualizarTarea() {

    }

    @Test
    void eliminarTarea() {
        tareaController.eliminarTarea(tarea.getId());
        Mockito.verify(tareaRepositorio).delete(tarea);
    }
}