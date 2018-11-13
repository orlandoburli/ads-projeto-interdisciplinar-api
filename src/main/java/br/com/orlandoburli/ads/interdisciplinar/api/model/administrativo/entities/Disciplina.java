package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Table(name = "disciplina")
@Entity
public class Disciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_disciplina")
	@SequenceGenerator(name = "seq_disciplina", initialValue = 1, allocationSize = 1, sequenceName = "seq_disciplina")
	private Integer id;

	@NotEmpty(message = "Campo nome é obrigatório")
	private String nome;

	@NotNull(message = "Campo carga horária é obrigatório")
	@Column(name = "carga_horaria")
	private Integer cargaHoraria;

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

	public Integer getCargaHoraria() {
		return this.cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
}