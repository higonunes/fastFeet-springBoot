package com.fastfeet.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import com.fastfeet.Services.Exception.AuthorizationException;

import com.fastfeet.Services.Exception.ObjectNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ResourceExceptionHandler
 */
@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(AuthorizationException.class)
  public ResponseEntity<StandardError> notAuthorized(AuthorizationException e, HttpServletRequest request) {
    StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Acesso negado", e.getMessage() ,request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
  }

    @ExceptionHandler(ObjectNotFound.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFound e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontrado", e.getMessage() ,request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}