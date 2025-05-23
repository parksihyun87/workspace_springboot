package com.example.madang.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "booktbl")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookid", nullable = false)
    private Integer id;

    @Column(name = "bookname")
    private String bookname;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "price", nullable = false)
    private Integer price;

    @ColumnDefault("100")
    @Column(name = "bookamount")
    private Integer bookamount;

    @OneToMany(mappedBy = "bookid")
    @JsonManagedReference
    private Set<OrderEntity > orderstbls = new LinkedHashSet<>();
    //리스트로 해도 됨.
}