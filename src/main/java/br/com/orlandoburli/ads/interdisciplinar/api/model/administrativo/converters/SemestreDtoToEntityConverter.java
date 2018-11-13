package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.converters;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.SemestreDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities.Semestre;

@Component
public class SemestreDtoToEntityConverter implements Converter<SemestreDTO, Semestre> {

	@Autowired
	private DisciplinaDtoToEntityConverter disciplinaConveter;

	@Override
	public Semestre convert(SemestreDTO source) {
		final Semestre destino = new Semestre();

		destino.setId(source.getId());
		destino.setNumero(source.getNumero());

		// @formatter:off
		destino.setDisciplinas(
				source
					.getDisciplinas()
					.stream()
					.map((a) -> this.disciplinaConveter.convert(a))
					.collect(Collectors.toList()));
		// @formatter:on

		return destino;
	}
}
