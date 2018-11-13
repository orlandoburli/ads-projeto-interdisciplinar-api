package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

	@Query("SELECT p FROM Professor p WHERE (UPPER(p.nome) LIKE UPPER(:filtro) OR UPPER(p.email) LIKE UPPER(:filtro) OR p.cpf LIKE :filtro)")
	public Page<Professor> findByNomeOrEmailOrCpf(@Param(value = "filtro") String filtro, Pageable pagination);
}
