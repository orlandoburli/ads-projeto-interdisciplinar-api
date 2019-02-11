package br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

	@Query("SELECT a FROM Aluno a WHERE (UPPER(a.nome) LIKE UPPER(:filtro) OR UPPER(a.email) LIKE UPPER(:filtro) OR UPPER(a.rg) LIKE UPPER(:filtro) OR a.cpf LIKE :filtro)")
	public Page<Aluno> findByNomeOrEmailOrCpfOrRg(@Param(value = "filtro") String filtro, Pageable pagination);

}
