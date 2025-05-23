package com.example.madang.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "orderstbl")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "custid", nullable = false)
    @JsonBackReference
    private CustomerEntity custid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bookid", nullable = false)
    @JsonBackReference
    private BookEntity bookid;

    @Column(name = "saleprice", nullable = false)
    private Integer saleprice;

    @Column(name = "orderdate", nullable = false)
    private LocalDate orderdate;

    @ColumnDefault("1")
    @Column(name = "ordamount")
    private Integer ordamount;

}