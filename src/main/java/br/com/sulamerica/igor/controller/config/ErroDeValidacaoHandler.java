package br.com.sulamerica.igor.controller.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorDeFormularioDTO> hadle(MethodArgumentNotValidException exception) {
		List<ErrorDeFormularioDTO> dto = new ArrayList<ErrorDeFormularioDTO>();
		List<FieldError> fieldErro = exception.getBindingResult().getFieldErrors();
		fieldErro.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErrorDeFormularioDTO erro = new ErrorDeFormularioDTO(e.getField(), mensagem);
			dto.add(erro);
		});

		return dto;
	}
}
