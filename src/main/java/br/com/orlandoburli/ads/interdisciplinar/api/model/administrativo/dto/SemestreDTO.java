package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SemestreDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer numero;

	private List<DisciplinaDTO> disciplinas;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<DisciplinaDTO> getDisciplinas() {
		if (this.disciplinas == null) {
			this.disciplinas = new ArrayList<>();
		}
		return this.disciplinas;
	}

	public void setDisciplinas(List<DisciplinaDTO> disciplinas) {
		this.disciplinas = disciplinas;
	}
}
