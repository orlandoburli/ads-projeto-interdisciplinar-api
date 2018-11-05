package br.com.orlandoburli.ads.interdisciplinar.api.resources.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.builder.UsuarioBuilder;
import br.com.orlandoburli.ads.interdisciplinar.api.model.exceptions.BusinessException;

@RestController
@RequestMapping("/gerarDados/v1")
public class DadosResources {

	@Autowired
	private UsuarioBuilder usuarioBuilder;

	@GetMapping("/usuarios/{quantidade}")
	public void gerarUsuarios(@PathVariable int quantidade) throws BusinessException {
		this.usuarioBuilder.createUsuarios(quantidade);
	}
}