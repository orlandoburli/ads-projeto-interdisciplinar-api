package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.UsuarioDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities.Usuario;

@Component
public class UsuarioEntityToDtoConverter implements Converter<Usuario, UsuarioDTO> {

	@Override
	public UsuarioDTO convert(Usuario source) {
		final UsuarioDTO destino = new UsuarioDTO();

		destino.setId(source.getId());
		destino.setNome(source.getNome());
		destino.setEmail(source.getEmail());
		destino.setCpf(source.getCpf());
		destino.setSenha(source.getSenha());

		return destino;
	}

}
