package br.com.orlandoburli.ads.interdisciplinar.api.model.geral.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoEndereco {

	RESIDENCIAL("R", "Residencial"), COMERCIAL("C", "Comercial");

	private final String valor;
	private final String descricao;

	private TipoEndereco(String valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public String getValor() {
		return this.valor;
	}

	@JsonValue
	public String getDescricao() {
		return this.descricao;
	}

	@JsonCreator
	public static TipoEndereco from(String requisicao) {
		for (final TipoEndereco s : TipoEndereco.values()) {
			if (s.getValor().equalsIgnoreCase(requisicao) || s.getDescricao().equalsIgnoreCase(requisicao)) {
				return s;
			}
		}
		return null;
	}
}
