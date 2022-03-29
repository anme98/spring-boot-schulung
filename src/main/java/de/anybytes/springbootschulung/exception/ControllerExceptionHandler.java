package de.anybytes.springbootschulung.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
@RequiredArgsConstructor // Erstellt den Autowired Constructor automatisch
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> entityNotFoundException(RuntimeException exception, WebRequest request) {
        String[] exceptionMessages = {exception.getMessage()};
        String error = messageSource.getMessage("entityNotFound", exceptionMessages, LocaleContextHolder.getLocale());
        return handleExceptionInternal(exception, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
