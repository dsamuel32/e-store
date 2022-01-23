package br.com.estore.orderquery.exceptions.handlers;

import br.com.estore.orderquery.domain.dtos.ExceptionResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleException(final Exception e) {
        log.error("RestExceptionHandler.handleException: {}", e.getMessage(), e);
        return buildResponseEntity(ExceptionResponseDTO.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .message(e.getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException e,
                                                               final HttpHeaders headers,
                                                               final HttpStatus status,
                                                               final WebRequest request) {
        log.error("RestExceptionHandler.handleMethodArgumentNotValid: {}", e.getMessage(), e);

        final Set<String> errors = e.getBindingResult().getFieldErrors().stream()
                .map(f -> String.format("%s : %s", f.getField(), f.getDefaultMessage()))
                .collect(Collectors.toSet());

        return buildResponseEntity(ExceptionResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.name())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .details(errors)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("RestExceptionHandler.handleIllegalArgumentException - start - message ['" + ex.getMessage() + "']", ex);
        return buildResponseEntity(ExceptionResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.name())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .details(new HashSet<>(Collections.singletonList(ex.getMessage())))
                .build(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> buildResponseEntity(final ExceptionResponseDTO apiError, final HttpStatus httpStatus) {
        return new ResponseEntity<>(apiError, httpStatus);
    }

}
