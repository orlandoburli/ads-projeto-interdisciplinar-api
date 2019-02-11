package br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.builder;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.builder.UsuarioBuilder;
import br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.dto.AlunoDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.dto.EnderecoDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.service.AlunoService;
import br.com.orlandoburli.ads.interdisciplinar.api.model.exceptions.BusinessException;
import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.enums.SimNao;
import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.enums.TipoEndereco;

@Service
public class AlunoBuilder {

	@Autowired
	private UsuarioBuilder usuarioService;

	@Autowired
	private AlunoService service;

	public void createAluno() throws BusinessException {
		final AlunoDTO a = new AlunoDTO();

		final Faker faker = new Faker();
		a.setNome(faker.name().fullName());
		a.setEmail(faker.name().username().toLowerCase() + "@" + faker.internet().domainName());
		a.setCpf(this.usuarioService.cpf(false));
		a.setSenha(faker.code().isbn13());
		a.setRg(this.rg());
		a.setEmissorRg("SSP/MT");

		final Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.setTime(faker.date().birthday());
		a.setDataNascimento(dataNascimento);

		a.setResetarSenha(SimNao.NAO);

		final EnderecoDTO endereco = new EnderecoDTO();
		a.getEnderecos().add(endereco);

		endereco.setCep(Integer.parseInt(this.cep()));
		endereco.setBairro(faker.address().firstName());
		endereco.setCidade(faker.address().city());
		endereco.setUf(faker.address().stateAbbr());
		endereco.setLogradouro(faker.address().streetName());
		endereco.setTipoEndereco(TipoEndereco.RESIDENCIAL);
		endereco.setComplemento(faker.address().secondaryAddress());

		this.service.insert(a);
	}

	public String rg() {
		final StringBuilder result = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			result.append(this.usuarioService.randomiza(9));
		}

		return result.toString();
	}

	public String cep() {
		final StringBuilder result = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			result.append(this.usuarioService.randomiza(9));
		}

		return result.toString();
	}

	public void createAlunos(int quantidade) throws BusinessException {
		for (int i = 0; i < quantidade; i++) {
			this.createAluno();
		}
	}
}
