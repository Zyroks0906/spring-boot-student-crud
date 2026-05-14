package com.example.demo.controller;

import com.example.demo.model.Estudiante;
import com.example.demo.service.EstudianteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EstudianteController.class)
class EstudianteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EstudianteService estudianteService;

    @Test
    void obtenerTodosRetornaStatus200ConListaJson() throws Exception {
        when(estudianteService.obtenerTodos()).thenReturn(List.of(
            new Estudiante(1L, "Juan", "juan@example.com", 20)
        ));

        mockMvc.perform(get("/api/estudiantes").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].nombre").value("Juan"))
            .andExpect(jsonPath("$[0].email").value("juan@example.com"))
            .andExpect(jsonPath("$[0].edad").value(20));
    }

    @Test
    void obtenerTodosRetornaStatus200ConListaVacia() throws Exception {
        when(estudianteService.obtenerTodos()).thenReturn(List.of());

        mockMvc.perform(get("/api/estudiantes").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void crearEstudianteRetornaStatus201ConEstudianteGuardado() throws Exception {
        Estudiante guardado = new Estudiante(1L, "María", "maria@example.com", 22);
        when(estudianteService.guardar(any(Estudiante.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/estudiantes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"María\",\"email\":\"maria@example.com\",\"edad\":22}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.nombre").value("María"))
            .andExpect(jsonPath("$.email").value("maria@example.com"));
    }
}
