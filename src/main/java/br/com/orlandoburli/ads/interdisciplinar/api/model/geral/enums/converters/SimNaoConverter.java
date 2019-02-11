package br.com.orlandoburli.ads.interdisciplinar.api.model.geral.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.enums.SimNao;

@Converter(autoApply = true)
public class SimNaoConverter implements AttributeConverter<SimNao, String> {

	@Override
	public String convertToDatabaseColumn(SimNao attribute) {
		return attribute == null ? null : attribute.getValor();
	}

	@Override
	public SimNao convertToEntityAttribute(String dbData) {
		return SimNao.from(dbData);
	}
}
