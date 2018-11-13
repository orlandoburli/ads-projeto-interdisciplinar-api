package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.converters;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.CursoDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities.Curso;

@Component
public class CursoEntityToDtoConverter implements Converter<Curso, CursoDTO> {

	@Autowired
	private SemestreEntityToDtoConverter semestreConverter;

	@Override
	public CursoDTO convert(Curso source) {
		final CursoDTO destino = new CursoDTO();

		// @formatter:off
		destino.setId(source.getId());
		destino.setNome(source.getNome());
		destino.setQuantidadeSemestres(source.getQuantidadeSemestres());
		destino.setSemestres(
				source
					.getSemestres()
					.stream()
					.map(a -> this.semestreConverter.convert(a))
					.collect(Collectors.toList())
				);
		destino.setStatus(source.getStatus());
		// @formatter:on

		return destino;
	}

}
