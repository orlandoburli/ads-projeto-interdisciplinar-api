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

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.CursoDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.DadosDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.requests.CursoConsultaRequest;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.service.CursoService;
import br.com.orlandoburli.ads.interdisciplinar.api.model.exceptions.BusinessException;
import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.ConsultaResponse;

@RestController
@RequestMapping("/curso/v1")
public class CursoResource {

	@Autowired
	private CursoService service;

	@GetMapping("{id}")
	public Optional<CursoDTO> get(@PathVariable Integer id) {
		return this.service.get(id);
	}

	@PostMapping
	public CursoDTO inserir(@RequestBody CursoDTO curso) throws BusinessException {
		return this.service.insert(curso);
	}

	@PutMapping
	public CursoDTO atualizar(@RequestBody CursoDTO curso) throws BusinessException {
		return this.service.update(curso);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) throws BusinessException {
		this.service.delete(id);
	}

	@GetMapping("dados")
	public DadosDTO dados() {
		return this.service.getDados();
	}

	@PostMapping("pesquisar")
	public ConsultaResponse<CursoDTO> pesquisar(@RequestBody CursoConsultaRequest request) {
		return this.service.pesquisar(request);
	}
}
