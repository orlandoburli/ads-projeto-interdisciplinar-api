package br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.enums.SimNao;

@Table(name = "aluno")
@Entity
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aluno")
	@SequenceGenerator(name = "seq_aluno", initialValue = 1, allocationSize = 1, sequenceName = "seq_aluno")
	private Integer id;

	@NotEmpty(message = "Campo nome é obrigatório")
	private String nome;

	@CPF
	@NotEmpty(message = "Campo cpf é obrigatório")
	private String cpf;

	@NotEmpty(message = "Campo RG é obrigatório")
	private String rg;

	@NotEmpty(message = "Campo emissor RG é obrigatório")
	private String emissorRg;

	@NotNull(message = "Campo data de nascimento é obrigatório")
	private Calendar dataNascimento;

	@Email
	private String email;

	private String senha;

	private SimNao resetarSenha;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "aluno_endereco", joinColumns = { @JoinColumn(name = "id_aluno") }, inverseJoinColumns = {
			@JoinColumn(name = "id_endereco") })
	private List<Endereco> enderecos;

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

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEmissorRg() {
		return this.emissorRg;
	}

	public void setEmissorRg(String emissorRg) {
		this.emissorRg = emissorRg;
	}

	public Calendar getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public SimNao getResetarSenha() {
		return this.resetarSenha;
	}

	public void setResetarSenha(SimNao resetarSenha) {
		this.resetarSenha = resetarSenha;
	}

	public List<Endereco> getEnderecos() {
		if (this.enderecos == null) {
			this.enderecos = new ArrayList<>();
		}
		return this.enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
}
