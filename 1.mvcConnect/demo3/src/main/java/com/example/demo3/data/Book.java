package com.example.demo3.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="booktbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
// 5가지 엔테데얼노
// 2가지 아제
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookid;
    private String bookname;
    private String publisher;
    private Integer price;
}
