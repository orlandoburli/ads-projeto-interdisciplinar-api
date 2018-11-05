package br.com.orlandoburli.ads.interdisciplinar.api.resources.administrativo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.UsuarioDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities.Usuario;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.requests.UsuarioConsultaRequest;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.service.UsuarioService;
import br.com.orlandoburli.ads.interdisciplinar.api.model.exceptions.BusinessException;
import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.ConsultaResponse;

@RestController
@RequestMapping("/usuario/v1")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;

	@GetMapping("/{id}")
	public Optional<Usuario> get(@PathVariable Integer id) {
		return this.service.get(id);
	}

	@PostMapping
	public UsuarioDTO inserir(@RequestBody UsuarioDTO usuario) throws BusinessException {
		return this.service.insert(usuario);
	}

	@PutMapping
	public UsuarioDTO atualizar(@RequestBody UsuarioDTO usuario) throws BusinessException {
		return this.service.update(usuario);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) throws BusinessException {
		this.service.delete(id);
	}

	@PostMapping("pesquisar")
	public ConsultaResponse<Usuario> pesquisar(@RequestBody UsuarioConsultaRequest request) {
		return this.service.pesquisar(request);
	}
}
