package com.example.madang.data.repository;

import com.example.madang.data.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookEntityRepository extends JpaRepository<BookEntity,Integer> {
}
