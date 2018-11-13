package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

	Page<Disciplina> findByNomeLikeIgnoreCase(String filtro, Pageable pageable);

}
