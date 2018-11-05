package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.UsuarioDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities.Usuario;

@Component
public class UsuarioDtoToEntityConverter implements Converter<UsuarioDTO, Usuario> {

	@Override
	public Usuario convert(UsuarioDTO source) {
		final Usuario destino = new Usuario();

		destino.setId(source.getId());
		destino.setNome(source.getNome());
		destino.setEmail(source.getEmail());
		destino.setCpf(source.getCpf());
		destino.setSenha(source.getSenha());

		return destino;
	}

}
