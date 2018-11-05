package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("SELECT u FROM Usuario u WHERE (UPPER(u.nome) LIKE UPPER(:filtro) OR UPPER(u.email) LIKE UPPER(:filtro) OR u.cpf LIKE :filtro)")
	public Page<Usuario> findByNomeOrEmailOrCpf(@Param(value = "filtro") String filtro, Pageable pagination);
}
