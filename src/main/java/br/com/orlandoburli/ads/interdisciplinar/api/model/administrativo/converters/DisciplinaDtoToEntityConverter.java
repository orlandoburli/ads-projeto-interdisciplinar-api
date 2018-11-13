package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.DisciplinaDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities.Disciplina;

@Component
public class DisciplinaDtoToEntityConverter implements Converter<DisciplinaDTO, Disciplina> {

	@Override
	public Disciplina convert(DisciplinaDTO source) {
		final Disciplina destino = new Disciplina();

		destino.setId(source.getId());
		destino.setNome(source.getNome());
		destino.setCargaHoraria(source.getCargaHoraria());

		return destino;
	}
}