package sii.ms_evalexamenes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sii.ms_evalexamenes.entities.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Long> {

    Optional<List<Materia>> findAllByIdConvocatoria(Long idConvocatoria);
    
}
