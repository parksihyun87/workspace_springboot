package com.example.madang.controller;

import com.example.madang.data.entity.BookEntity;
import com.example.madang.data.entity.OrderEntity;
import com.example.madang.data.repository.BookEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookEntityRepository bookEntityRepository;

    @GetMapping(value="/booklist")
    public List<BookEntity> getBooklist(){
        return bookEntityRepository.findAll();
    }

    @GetMapping(value="/book/{id}")
    public Set<OrderEntity> getBooklist(@PathVariable Integer id){
        BookEntity book= this.bookEntityRepository.findById(id).orElse(null);
        Set<OrderEntity> list = book.getOrderstbls();
        return list;
    }
}

// all ordertbl list
// bookid->detailed booktbl inform
// bookid-> oredered book list
