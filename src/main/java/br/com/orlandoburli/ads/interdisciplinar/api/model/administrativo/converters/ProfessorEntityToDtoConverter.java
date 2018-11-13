package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.ProfessorDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities.Professor;

@Component
public class ProfessorEntityToDtoConverter implements Converter<Professor, ProfessorDTO> {

	@Override
	public ProfessorDTO convert(Professor source) {
		final ProfessorDTO destino = new ProfessorDTO();

		destino.setId(source.getId());
		destino.setNome(source.getNome());
		destino.setEmail(source.getEmail());
		destino.setCpf(source.getCpf());
		destino.setSenha(source.getSenha());

		return destino;
	}

}
