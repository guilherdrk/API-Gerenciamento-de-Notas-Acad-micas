package com.example.gerenciadornotas.model.repository;

import com.example.gerenciadornotas.model.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

}
