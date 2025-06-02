package com.example.gerenciadornotas.controller;

import com.example.gerenciadornotas.model.dto.NotaDTO;
import com.example.gerenciadornotas.model.entity.Nota;
import com.example.gerenciadornotas.service.NotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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


    @Operation(summary = "Retorna uma nota por ID", description = "Busca uma nota específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nota encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nota não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> getNotaById(
            @Parameter(description = "ID da nota a ser buscada") @PathVariable Long id) {
        NotaDTO nota = notaService.getNotaById(id);
        return ResponseEntity.ok(nota);
    }

    @Operation(summary = "Lista todas as notas", description = "Retorna uma lista paginada e ordenada de todas as notas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de notas retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity <Page<NotaDTO>> getAllNotas(
            @Parameter(description = "Número da página (0-indexed)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamanho da página") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Campo para ordenação (ex: tituloCadeira, valorNota, dataLancamento)") @RequestParam(defaultValue = "id") String sortBy,
            @Parameter(description = "Direção da ordenação (asc ou desc)") @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Page<NotaDTO> notas = notaService.getAllNotas(page, size, sortBy, sortDirection);
        return ResponseEntity.ok(notas);
    }

    @Operation(summary = "Atualiza uma nota existente", description = "Atualiza os dados de uma nota pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nota atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (erros de validação)"),
            @ApiResponse(responseCode = "404", description = "Nota não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<NotaDTO> atualizarNota(
            @Parameter(description = "ID da nota a ser atualizada") @PathVariable Long id,
            @Valid @RequestBody NotaDTO notaDTO) {
        NotaDTO notaAtualizada = notaService.atualizarNota(id, notaDTO);
        return ResponseEntity.ok(notaAtualizada);
    }

    @Operation(summary = "Deleta uma nota", description = "Remove uma nota do sistema pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nota deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nota não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNota(
            @Parameter(description = "ID da nota a ser deletada") @PathVariable Long id) {
        notaService.deletarNota(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 No Content
    }

}
