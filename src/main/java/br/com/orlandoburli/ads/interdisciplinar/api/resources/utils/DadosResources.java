package br.com.orlandoburli.ads.interdisciplinar.api.resources.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.builder.ProfessorBuilder;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.builder.UsuarioBuilder;
import br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.builder.AlunoBuilder;
import br.com.orlandoburli.ads.interdisciplinar.api.model.exceptions.BusinessException;

@RestController
@RequestMapping("/gerarDados/v1")
public class DadosResources {

	@Autowired
	private UsuarioBuilder usuarioBuilder;

	@Autowired
	private ProfessorBuilder professorBuilder;

	@Autowired
	private AlunoBuilder alunoBuilder;

	@GetMapping("/usuarios/{quantidade}")
	public void gerarUsuarios(@PathVariable int quantidade) throws BusinessException {
		this.usuarioBuilder.createUsuarios(quantidade);
	}

	@GetMapping("/professores/{quantidade}")
	public void gerarProfessores(@PathVariable int quantidade) throws BusinessException {
		this.professorBuilder.createProfessores(quantidade);
	}

	@GetMapping("/alunos/{quantidade}")
	public void gerarAlunos(@PathVariable int quantidade) throws BusinessException {
		this.alunoBuilder.createAlunos(quantidade);
	}
}