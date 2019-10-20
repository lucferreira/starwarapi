package br.com.desafiob2w.starwarapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.Getter;

@ControllerAdvice
public class PlanetaResourceExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, new Mensagens("Operação não permitida",ex.getMessage().toString()), headers, HttpStatus.METHOD_NOT_ALLOWED, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, new Mensagens("Informar dados para inclusão",ex.getMessage().toString()),headers, HttpStatus.BAD_REQUEST, request);
	}
	
	public static class Mensagens{
		@Getter
		private String mensagemUsuario;
		@Getter
		private String mensagemDesenv;
		
		public Mensagens(String mensagemUsuario, String mensagemDesenv) {
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenv = mensagemDesenv;
		}
	
	}
	
}
