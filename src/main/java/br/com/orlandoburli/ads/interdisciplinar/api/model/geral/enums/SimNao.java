package br.com.orlandoburli.ads.interdisciplinar.api.model.geral.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SimNao {

	SIM("S", "Sim"), NAO("N", "Não");

	private final String valor;
	private final String descricao;

	private SimNao(String valor, String descricao) {
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
	public static SimNao from(String requisicao) {
		for (final SimNao s : SimNao.values()) {
			if (s.getValor().equalsIgnoreCase(requisicao) || s.getDescricao().equalsIgnoreCase(requisicao)) {
				return s;
			}
		}
		return null;
	}
}
