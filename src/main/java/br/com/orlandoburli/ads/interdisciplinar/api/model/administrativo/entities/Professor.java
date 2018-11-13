package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

@Table(name = "professor")
@Entity
public class Professor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_professor")
	@SequenceGenerator(name = "seq_professor", initialValue = 1, allocationSize = 1, sequenceName = "seq_professor")
	private Integer id;

	@NotEmpty(message = "Nome é obrigatório")
	private String nome;

	@NotEmpty(message = "Email é obrigatório")
	@Email(message = "Email inválido")
	private String email;

	@NotEmpty(message = "CPF é obrigatório")
	@CPF(message = "CPF inválido")
	private String cpf;

	private String senha;

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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
