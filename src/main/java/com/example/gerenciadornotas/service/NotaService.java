package com.example.gerenciadornotas.service;

import com.example.gerenciadornotas.exception.ResourceNotFoundException;
import com.example.gerenciadornotas.model.dto.NotaDTO;
import com.example.gerenciadornotas.model.entity.Nota;
import com.example.gerenciadornotas.model.repository.NotaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NotaService {

    private final NotaRepository notaRepository;

    public NotaService(NotaRepository notaRepository){
        this.notaRepository = notaRepository;
    }

    // Criar uma nova nota
    public NotaDTO criarNota(NotaDTO notaDTO){
        Nota nota = new Nota();
        nota.setTituloCadeira(notaDTO.getTituloCadeira());
        nota.setValorNota(notaDTO.getValorNota());
        nota.setDataLancamento(notaDTO.getDataLancamento());
        nota.setObservacoes(notaDTO.getObservacoes());
        nota.setId(null);

        Nota savedNota = notaRepository.save(nota);

        notaDTO.setId(savedNota.getId());
        notaDTO.setTituloCadeira(savedNota.getTituloCadeira());
        notaDTO.setValorNota(savedNota.getValorNota());
        notaDTO.setDataLancamento(savedNota.getDataLancamento());
        notaDTO.setObservacoes(savedNota.getObservacoes());

        return notaDTO;
    }
    // Obter nota por ID
    public NotaDTO getNotaById(Long id){
        Nota nota = notaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nota não encontrada com ID: " + id));
        NotaDTO notaDTO = new NotaDTO();
        BeanUtils.copyProperties(nota, notaDTO);
        return notaDTO;
    }
    // Listar todas as notas com paginação e
    public Page<NotaDTO> getAllNotas(int page, int size, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Nota> notasPage = notaRepository.findAll(pageable);

        return notasPage.map(nota -> {
            NotaDTO dto = new NotaDTO();

            BeanUtils.copyProperties(nota, dto);

            return dto;
        });

    }
    // Atualizar uma nota existente
    public NotaDTO atualizarNota(Long id, NotaDTO notaDTO){
        Nota notaExistente = notaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nota não encontrada com ID: " + id));
        BeanUtils.copyProperties(notaDTO, notaExistente, "id");
        notaExistente.setId(id);
        Nota updatedNota = notaRepository.save(notaExistente);
        BeanUtils.copyProperties(updatedNota, notaDTO);
        return notaDTO;
    }
    // Deletar uma nota
    public void deletarNota(Long id){
        if(!notaRepository.existsById(id)){
         throw  new ResourceNotFoundException("Nota não encontrada com ID: " + id);
        }
        notaRepository.deleteById(id);
    }

}
