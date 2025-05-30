package com.example.gerenciadornotas.controller;

import com.example.gerenciadornotas.model.dto.NotaDTO;
import com.example.gerenciadornotas.model.entity.Nota;
import com.example.gerenciadornotas.service.NotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Operation(summary = "Cria uma nova nota", description = "Adiciona uma nova nota de cadeira ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Nota criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (erros de validação")
    })
    @PostMapping
    public ResponseEntity<NotaDTO> criarNota(@Valid @RequestBody NotaDTO notaDTO){
       NotaDTO novaNota = notaService.criarNota(notaDTO);
       return new ResponseEntity<>(notaDTO, HttpStatus.CREATED);
    }


}
