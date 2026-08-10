package com.yeirel.StockCR.exception;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public  ResponseMsg handleNotFoundException( ResourceNotFoundException ex){
        ResponseMsg resMsg = new ResponseMsg(ex.getMessage());
        return resMsg;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public  ResponseMsg handleBadRequestException( BadRequestException ex){
        ResponseMsg resMsg = new ResponseMsg(ex.getMessage());
        return resMsg;
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public  ResponseMsg handleConflictException( ConflictException ex){
        ResponseMsg resMsg = new ResponseMsg(ex.getMessage());
        return resMsg;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMsg handleValidationException(MethodArgumentNotValidException ex) {

        String message = "Error de validación.";

        if (ex.getBindingResult().getFieldError() != null) {
            message = ex.getBindingResult()
                    .getFieldError()
                    .getDefaultMessage();
        }

        return new ResponseMsg(message);
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseMsg handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseMsg("El correo electrónico o la contraseña son incorrectos.");
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMsg handleConstraintViolationException(ConstraintViolationException ex) {
        log.warn("Violación de validación a nivel de entidad: {}", ex.getMessage());
        return new ResponseMsg("Uno de los datos generados no cumple las reglas de validación: " + ex.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseMsg handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return new ResponseMsg(ex.getMessage());
    }

    @ExceptionHandler(TooManyRequestsException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public ResponseMsg handleTooManyRequestsException(TooManyRequestsException ex) {
        return new ResponseMsg(ex.getMessage());
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseMsg handleAccessDeniedException(org.springframework.security.access.AccessDeniedException ex) {
        log.warn("Acceso denegado: {}", ex.getMessage());
        return new ResponseMsg("No tienes permiso para realizar esta acción.");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMsg handleGlobalException(Exception ex) {
        log.error("Error inesperado no controlado", ex);
        return new ResponseMsg("Ocurrió un error inesperado en el servidor. Inténtalo de nuevo más tarde.");
    }

}
