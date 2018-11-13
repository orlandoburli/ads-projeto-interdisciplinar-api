package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.enums.Status;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CursoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private Integer quantidadeSemestres;

	private Status status;

	private List<SemestreDTO> semestres;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getQuantidadeSemestres() {
		return this.quantidadeSemestres;
	}

	public void setQuantidadeSemestres(Integer quantidadeSemestres) {
		this.quantidadeSemestres = quantidadeSemestres;
	}

	public List<SemestreDTO> getSemestres() {
		if (this.semestres == null) {
			this.semestres = new ArrayList<>();
		}
		return this.semestres;
	}

	public void setSemestres(List<SemestreDTO> semestres) {
		this.semestres = semestres;
	}
}
