package br.com.orlandoburli.ads.interdisciplinar.api.model.exceptions;

import java.util.Map;
import java.util.Set;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	private final Map<String, Set<String>> errors;

	public BusinessException(String message, Map<String, Set<String>> errors) {
		super(message);
		this.errors = errors;
	}

	public Map<String, Set<String>> getErrors() {
		return this.errors;
	}
}
