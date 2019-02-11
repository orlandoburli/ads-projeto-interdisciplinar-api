package br.com.orlandoburli.ads.interdisciplinar.api.model.geral.enums.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.enums.TipoEndereco;

@Converter(autoApply = true)
public class TipoEnderecoConverter implements AttributeConverter<TipoEndereco, String> {

	@Override
	public String convertToDatabaseColumn(TipoEndereco attribute) {
		return attribute == null ? null : attribute.getValor();
	}

	@Override
	public TipoEndereco convertToEntityAttribute(String dbData) {
		return TipoEndereco.from(dbData);
	}

}
