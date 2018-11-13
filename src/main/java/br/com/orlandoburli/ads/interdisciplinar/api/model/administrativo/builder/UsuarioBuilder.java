package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.UsuarioDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.service.UsuarioService;
import br.com.orlandoburli.ads.interdisciplinar.api.model.exceptions.BusinessException;

@Service
public class UsuarioBuilder {

	@Autowired
	private UsuarioService service;

	public void createUsuario() throws BusinessException {
		final UsuarioDTO u = new UsuarioDTO();

		final Faker faker = new Faker();
		u.setNome(faker.name().fullName());
		u.setEmail(faker.name().username().toLowerCase() + "@gmail.com");
		u.setCpf(this.cpf(false));
		u.setSenha(faker.code().isbn13());

		this.service.insert(u);
	}

	public void createUsuarios(int quantidade) throws BusinessException {
		for (int i = 0; i < quantidade; i++) {
			this.createUsuario();
		}
	}

	public String cpf(boolean comPontos) {
		final int n = 9;
		final int n1 = this.randomiza(n);
		final int n2 = this.randomiza(n);
		final int n3 = this.randomiza(n);
		final int n4 = this.randomiza(n);
		final int n5 = this.randomiza(n);
		final int n6 = this.randomiza(n);
		final int n7 = this.randomiza(n);
		final int n8 = this.randomiza(n);
		final int n9 = this.randomiza(n);
		int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

		d1 = 11 - this.mod(d1, 11);

		if (d1 >= 10) {
			d1 = 0;
		}

		int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

		d2 = 11 - this.mod(d2, 11);

		String retorno = null;

		if (d2 >= 10) {
			d2 = 0;
		}
		retorno = "";

		if (comPontos) {
			retorno = "" + n1 + n2 + n3 + '.' + n4 + n5 + n6 + '.' + n7 + n8 + n9 + '-' + d1 + d2;
		} else {
			retorno = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2;
		}

		return retorno;
	}

	private int randomiza(int n) {
		final int ranNum = (int) (Math.random() * n);
		return ranNum;
	}

	private int mod(int dividendo, int divisor) {
		return (int) Math.round(dividendo - Math.floor(dividendo / divisor) * divisor);
	}

}
