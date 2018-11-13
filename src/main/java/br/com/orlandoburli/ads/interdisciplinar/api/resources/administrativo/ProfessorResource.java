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

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.ProfessorDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.requests.ProfessorConsultaRequest;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.service.ProfessorService;
import br.com.orlandoburli.ads.interdisciplinar.api.model.exceptions.BusinessException;
import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.ConsultaResponse;

@RestController
@RequestMapping("/professor/v1")
public class ProfessorResource {

	@Autowired
	private ProfessorService service;

	@GetMapping("{id}")
	public Optional<ProfessorDTO> get(@PathVariable Integer id) {
		return this.service.get(id);
	}

	@PostMapping
	public ProfessorDTO inserir(@RequestBody ProfessorDTO professor) throws BusinessException {
		return this.service.insert(professor);
	}

	@PutMapping
	public ProfessorDTO atualizar(@RequestBody ProfessorDTO professor) throws BusinessException {
		return this.service.update(professor);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) throws BusinessException {
		this.service.delete(id);
	}

	@PostMapping("pesquisar")
	public ConsultaResponse<ProfessorDTO> pesquisar(@RequestBody ProfessorConsultaRequest request) {
		return this.service.pesquisar(request);
	}
}
