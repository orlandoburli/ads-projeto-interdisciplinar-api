package br.com.orlandoburli.ads.interdisciplinar.api.model.geral;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.orlandoburli.ads.interdisciplinar.api.model.exceptions.BusinessException;

public class CadastroResponse<T> {

	private T vo;

	private int status;

	private boolean success;

	private String message;

	private Map<String, Set<String>> errors;

	public CadastroResponse(T vo, boolean success, String message) {
		this.vo = vo;
		this.success = success;
		this.message = message;
		this.errors = new HashMap<>();
	}

	public CadastroResponse<T> withException(BusinessException ex) {
		this.setErrors(ex.getErrors());
		return this;
	}

	public T getVo() {
		return this.vo;
	}

	public void setVo(T vo) {
		this.vo = vo;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Set<String>> getErrors() {
		return this.errors;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setErrors(Map<String, Set<String>> errors) {
		this.errors = errors;
	}
}
