package br.com.orlandoburli.ads.interdisciplinar.api.resources.aluno;

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

import br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.dto.AlunoDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.requests.AlunoConsultaRequest;
import br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.service.AlunoService;
import br.com.orlandoburli.ads.interdisciplinar.api.model.exceptions.BusinessException;
import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.ConsultaResponse;

@RestController
@RequestMapping("/aluno/v1")
public class AlunoResource {

	@Autowired
	private AlunoService service;

	@GetMapping("{id}")
	public Optional<AlunoDTO> get(@PathVariable Integer id) {
		return this.service.get(id);
	}

	@PostMapping
	public AlunoDTO inserir(@RequestBody AlunoDTO aluno) throws BusinessException {
		return this.service.insert(aluno);
	}

	@PutMapping
	public AlunoDTO atualizar(@RequestBody AlunoDTO aluno) throws BusinessException {
		return this.service.update(aluno);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) throws BusinessException {
		this.service.delete(id);
	}

	@PostMapping("pesquisar")
	public ConsultaResponse<AlunoDTO> pesquisar(@RequestBody AlunoConsultaRequest request) {
		return this.service.pesquisar(request);
	}
}
