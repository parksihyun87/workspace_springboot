package com.example.teststorebackend.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // http json 문자열 바디부의 오류 다 받음.
public class ProductException {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error)->{
            errors.put(error.getField(),error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(value= HttpMessageNotReadableException.class)// 바디의 내용이나 틀이 잘못되었었을때 포맷에 %이거잘못되거나의경우
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("본문오류: "+ex.getMessage());
    }

    @ExceptionHandler(value= MethodArgumentTypeMismatchException.class)// 파라미터로 뭔가 들어올떄 문제가 생김.패스배리어블 하는데 문제가 생김.인테저인데 스트링오거나,리퀘스트 파람으로 쿼리 문자열 오거나
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        String message = String.format("파라미터 오류: value :'%s'. expected type : '%s'",
        ex.getValue(),
        ex.getRequiredType().getSimpleName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(value= EntityNotFoundException.class)// 원뜻은 아니지만 셀렉했을때 없다면 고의로 이 오류를 발생시킨다.
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("DB 검색오류" +ex.getMessage());//ex.getmessage(서비스가 넘겨준 문자열을 출력함)
    }

    @ExceptionHandler(value=MyException.class)
    public ResponseEntity<String> handleMyException(MyException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("사용자정의예외"+ex.getMessage());
    }
}
