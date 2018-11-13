package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name = "semestre")
@Entity
public class Semestre implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_semestre")
	@SequenceGenerator(name = "seq_semestre", initialValue = 1, allocationSize = 1, sequenceName = "seq_semestre")
	private Integer id;

	@NotNull(message = "Campo Número é obrigatório")
	private Integer numero;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "semestre_disciplina", joinColumns = { @JoinColumn(name = "id_semestre") }, inverseJoinColumns = {
			@JoinColumn(name = "id_disciplina") })
	private List<Disciplina> disciplinas;

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

	public List<Disciplina> getDisciplinas() {
		if (this.disciplinas == null) {
			this.disciplinas = new ArrayList<>();
		}
		return this.disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

}
