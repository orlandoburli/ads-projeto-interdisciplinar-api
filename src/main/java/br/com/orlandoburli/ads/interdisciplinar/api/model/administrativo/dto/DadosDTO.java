package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DadosDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<DisciplinaDTO> disciplinas;

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
