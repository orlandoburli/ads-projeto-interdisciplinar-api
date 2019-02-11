package br.com.orlandoburli.ads.interdisciplinar.api.model.aluno.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.enums.SimNao;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AlunoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private String cpf;

	private String rg;

	private String emissorRg;

	private Calendar dataNascimento;

	private String email;

	private String senha;

	private SimNao resetarSenha;

	private List<EnderecoDTO> enderecos;

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

	public List<EnderecoDTO> getEnderecos() {
		if (this.enderecos == null) {
			this.enderecos = new ArrayList<>();
		}
		return this.enderecos;
	}

	public void setEnderecos(List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}
}
