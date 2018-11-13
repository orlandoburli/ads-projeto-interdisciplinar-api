package br.com.orlandoburli.ads.interdisciplinar.api.model.geral.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.enums.Status;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

	@Override
	public String convertToDatabaseColumn(Status attribute) {
		return attribute == null ? null : attribute.getValor();
	}

	@Override
	public Status convertToEntityAttribute(String dbData) {
		return Status.from(dbData);
	}

}
