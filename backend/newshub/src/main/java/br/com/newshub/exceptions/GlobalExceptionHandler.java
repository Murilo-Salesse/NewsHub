package br.com.newshub.exceptions;

import br.com.newshub.response.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Trata erros de validação (ex: @NotBlank, @Email etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseModel<Map<String, Object>>> handleValidationException(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            Map<String, String> errorDetail = new HashMap<>();

            errorDetail.put("field", ((FieldError) error).getField());
            errorDetail.put("message", error.getDefaultMessage());
            errors.add(errorDetail);
        });

        Map<String, Object> data = new HashMap<>();
        data.put("errors", errors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseModel<>(data, "Erro de validação"));
    }

    // Caso queira capturar outras exceções genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseModel<String>> handleGenericException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseModel<>(null, "Erro interno: " + ex.getMessage()));
    }
}
