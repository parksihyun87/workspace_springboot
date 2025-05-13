package com.example.demo3.controller;

import com.example.demo3.data.Book;
import com.example.demo3.data.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor // 필수 파이널을 채우는 빈 객체 주입
public class BookController {
    private final BookRepository bookRepository;

    @GetMapping(value = "/booklist")// 무조건 소문자만 씀.
    public List<Book> bookList(){
        return bookRepository.findAll();
    } // 리스트로 모든 북을 받아 바디 부에 담음.

    @GetMapping(value = "/book/{id}")
    public Book book(@PathVariable Integer id){
        Optional<Book> book = this.bookRepository.findById(id);
        if(book.isPresent()){
            return book.get();
        }
        return null;
    }

    @PostMapping(value = "/book")
    public Book addbook(@RequestBody Book book){
        return this.bookRepository.save(book);// 이거는 엔티티
    }// 안됨

    @DeleteMapping(value = "/book/{id}")
    public String deletebook(@PathVariable Integer id){
        if(this.bookRepository.existsById(id)){
            this.bookRepository.deleteById(id);// 이거는 보이드
            return "Deleted Book";
        }
        return "Delete Failed";
    }
}
