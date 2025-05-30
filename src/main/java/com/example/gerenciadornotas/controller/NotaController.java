package com.example.gerenciadornotas.controller;

import com.example.gerenciadornotas.model.entity.Nota;
import com.example.gerenciadornotas.service.NotaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/api/notas")
@Tag(name = "Notas", description = "API para gerenciamento de notas acadêmicas")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService){
        this.notaService = notaService;
    }




}
