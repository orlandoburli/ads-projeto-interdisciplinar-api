package br.com.orlandoburli.ads.interdisciplinar.api.model.geral.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {

	ATIVO("A", "Ativo"), INATIVO("I", "Inativo");

	private final String valor;
	private final String descricao;

	private Status(String valor, String descricao) {
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
	public static Status from(String requisicao) {
		for (final Status s : Status.values()) {
			if (s.getValor().equalsIgnoreCase(requisicao) || s.getDescricao().equalsIgnoreCase(requisicao)) {
				return s;
			}
		}
		return null;
	}
}
