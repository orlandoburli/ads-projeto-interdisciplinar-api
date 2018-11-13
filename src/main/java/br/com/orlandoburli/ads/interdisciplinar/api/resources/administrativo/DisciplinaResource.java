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

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.DisciplinaDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.requests.DisciplinaConsultaRequest;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.service.DisciplinaService;
import br.com.orlandoburli.ads.interdisciplinar.api.model.exceptions.BusinessException;
import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.ConsultaResponse;

@RestController
@RequestMapping("/disciplina/v1")
public class DisciplinaResource {

	@Autowired
	private DisciplinaService service;

	@GetMapping("{id}")
	public Optional<DisciplinaDTO> get(@PathVariable Integer id) {
		return this.service.get(id);
	}

	@PostMapping
	public DisciplinaDTO inserir(@RequestBody DisciplinaDTO disciplina) throws BusinessException {
		return this.service.insert(disciplina);
	}

	@PutMapping
	public DisciplinaDTO atualizar(@RequestBody DisciplinaDTO disciplina) throws BusinessException {
		return this.service.update(disciplina);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) throws BusinessException {
		this.service.delete(id);
	}

	@PostMapping("pesquisar")
	public ConsultaResponse<DisciplinaDTO> pesquisar(@RequestBody DisciplinaConsultaRequest request) {
		return this.service.pesquisar(request);
	}
}
