package br.com.orlandoburli.ads.interdisciplinar.api.handlers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.orlandoburli.ads.interdisciplinar.api.model.exceptions.BusinessException;
import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.CadastroResponse;

@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ TransactionSystemException.class })
	public ResponseEntity<?> handleConstraintViolation(final Exception ex) {

		final Throwable cause = ((TransactionSystemException) ex).getRootCause();
		if (cause instanceof ConstraintViolationException) {
			final Set<ConstraintViolation<?>> constraintViolationException = ((ConstraintViolationException) cause)
					.getConstraintViolations();

			final Map<String, Set<String>> validations = this
					.buildMessagesConstraintViolation(constraintViolationException);
			final CadastroResponse<Object> build = new CadastroResponse<>(null, false, "Validação de dados");

			build.setStatus(HttpStatus.BAD_REQUEST.value());
			build.setErrors(validations);

			return new ResponseEntity<>(build, HttpStatus.BAD_REQUEST);
		}

		return null;
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		final ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);

		if (ex.getCause() instanceof InvalidFormatException) {
			final InvalidFormatException ifx = (InvalidFormatException) ex.getCause();

			ifx.printStackTrace();

			return new ResponseEntity<>(
					new CadastroResponse<>((Object) null, false,
							"Valor \"" + ifx.getValue() + "\" inválido para o atributo \""
									+ ifx.getPath().get(ifx.getPath().size() - 1).getFieldName() + "\"!"),
					responseStatus == null ? HttpStatus.BAD_REQUEST : responseStatus.value());
		}

		return new ResponseEntity<>(new CadastroResponse<>((Object) null, false, ex.getMessage()),
				responseStatus == null ? HttpStatus.BAD_REQUEST : responseStatus.value());
	}

	@ExceptionHandler({ BusinessException.class })
	public ResponseEntity<?> handleBusinessException(final BusinessException ex) {

		final ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);

		return new ResponseEntity<>(new CadastroResponse<>((Object) null, false, ex.getMessage()).withException(ex),
				responseStatus == null ? HttpStatus.BAD_REQUEST : responseStatus.value());
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders headers, HttpStatus status,
			WebRequest request) {

		final List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();

		final Map<String, Set<String>> validations = fieldErrors.stream().collect(Collectors.groupingBy(
				FieldError::getField, Collectors.mapping(FieldError::getDefaultMessage, Collectors.toSet())));

		final CadastroResponse<Object> build = new CadastroResponse<>(null, false, "Validation Error Field");

		build.setStatus(HttpStatus.BAD_REQUEST.value());
		build.setErrors(validations);

		return new ResponseEntity<>(build, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Constroi um mapa de mensagens com os campos de validação
	 *
	 * @param constraintViolationException
	 *            ConstraintViolationException
	 * @return mapa de validações
	 */
	private Map<String, Set<String>> buildMessagesConstraintViolation(
			final Set<ConstraintViolation<?>> constraintViolationException) {
		final Map<String, Set<String>> messages = new HashMap<>();

		constraintViolationException.forEach(message -> {
			final String key = ((PathImpl) message.getPropertyPath()).getLeafNode().getName();

			Set<String> messagesConstraints = messages.get(key);
			if (messagesConstraints == null) {
				messagesConstraints = new HashSet<>();
			}
			messagesConstraints.add(message.getMessage());

			messages.put(key, messagesConstraints);
		});

		return messages;
	}
}
