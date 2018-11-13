package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.ProfessorDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.service.ProfessorService;
import br.com.orlandoburli.ads.interdisciplinar.api.model.exceptions.BusinessException;

@Service
public class ProfessorBuilder {

	@Autowired
	private UsuarioBuilder usuarioService;

	@Autowired
	private ProfessorService service;

	public void createProfessor() throws BusinessException {
		final ProfessorDTO p = new ProfessorDTO();

		final Faker faker = new Faker();
		p.setNome(faker.name().fullName());
		p.setEmail(faker.name().username().toLowerCase() + "@gmail.com");
		p.setCpf(this.usuarioService.cpf(false));
		p.setSenha(faker.code().isbn13());

		this.service.insert(p);
	}

	public void createProfessores(int quantidade) throws BusinessException {
		for (int i = 0; i < quantidade; i++) {
			this.createProfessor();
		}
	}
}
