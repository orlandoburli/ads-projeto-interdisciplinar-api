package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.converters;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.CursoDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities.Curso;

@Component
public class CursoDtoToEntityConverter implements Converter<CursoDTO, Curso> {

	@Autowired
	private SemestreDtoToEntityConverter semestreConverter;

	@Override
	public Curso convert(CursoDTO source) {
		final Curso destino = new Curso();

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
