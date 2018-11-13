package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.enums.Status;

@Table(name = "curso")
@Entity
public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_curso")
	@SequenceGenerator(name = "seq_curso", initialValue = 1, allocationSize = 1, sequenceName = "seq_curso")
	private Integer id;

	@NotEmpty(message = "Campo nome é obrigatório")
	private String nome;

	@NotNull(message = "Campo semestres é obrigatório")
	@Column(name = "semestres")
	private Integer quantidadeSemestres;

	@NotNull(message = "Campo status é obrigatório")
	private Status status;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "id_curso")
	private List<Semestre> semestres;

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

	public List<Semestre> getSemestres() {
		if (this.semestres == null) {
			this.semestres = new ArrayList<>();
		}
		return this.semestres;
	}

	public void setSemestres(List<Semestre> semestres) {
		this.semestres = semestres;
	}
}
