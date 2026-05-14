package com.example.demo.controller;

import com.example.demo.model.Estudiante;
import com.example.demo.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<Estudiante>> obtenerTodos() {
        return ResponseEntity.ok(estudianteService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<Estudiante> crear(@RequestBody Estudiante estudiante) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.guardar(estudiante));
    }
}
