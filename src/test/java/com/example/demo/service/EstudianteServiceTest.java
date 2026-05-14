package com.example.demo.service;

import com.example.demo.model.Estudiante;
import com.example.demo.repository.EstudianteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EstudianteServiceTest {

    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private EstudianteService estudianteService;

    @Test
    void guardarEstudianteRetornaEstudianteGuardado() {
        Estudiante entrada = new Estudiante(null, "Juan", "juan@example.com", 20);
        Estudiante guardado = new Estudiante(1L, "Juan", "juan@example.com", 20);
        when(estudianteRepository.save(entrada)).thenReturn(guardado);

        Estudiante resultado = estudianteService.guardar(entrada);

        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getNombre()).isEqualTo("Juan");
        assertThat(resultado.getEmail()).isEqualTo("juan@example.com");
        verify(estudianteRepository).save(entrada);
    }

    @Test
    void obtenerTodosRetornaListaCompleta() {
        List<Estudiante> estudiantes = List.of(
            new Estudiante(1L, "Juan", "juan@example.com", 20),
            new Estudiante(2L, "María", "maria@example.com", 22)
        );
        when(estudianteRepository.findAll()).thenReturn(estudiantes);

        List<Estudiante> resultado = estudianteService.obtenerTodos();

        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getNombre()).isEqualTo("Juan");
        assertThat(resultado.get(1).getNombre()).isEqualTo("María");
        verify(estudianteRepository).findAll();
    }

    @Test
    void obtenerTodosRetornaListaVaciaSiNoHayEstudiantes() {
        when(estudianteRepository.findAll()).thenReturn(List.of());

        List<Estudiante> resultado = estudianteService.obtenerTodos();

        assertThat(resultado).isEmpty();
        verify(estudianteRepository).findAll();
    }
}
