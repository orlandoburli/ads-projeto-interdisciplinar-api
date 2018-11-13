package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities.Curso;
import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.enums.Status;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

	@Query("SELECT c FROM Curso c WHERE UPPER(c.nome) LIKE UPPER(:filtro) AND (c.status = :status OR :status IS NULL)")
	Page<Curso> findByNomeAndStatus(@Param(value = "filtro") String filtro, @Param(value = "status") Status status,
			Pageable pageable);

}
