package com.example.madang.data.dao;

import com.example.madang.data.entity.BookEntity;
import com.example.madang.data.repository.BookEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookDAO {
    private final BookEntityRepository bookEntityRepository;

    public BookEntity getBookInfo(Integer id) {
        Optional<BookEntity> bookEntity = bookEntityRepository.findById(id);
        return bookEntity.orElse(null);
    }
}
