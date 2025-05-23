package com.example.madang.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "customertbl")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "custid", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "address", nullable = false, length = 20)
    private String address;

    @Column(name = "phone", length = 20)
    private String phone;

    @ColumnDefault("'bronze'")
    @Column(name = "custrate", length = 20)
    private String custrate;

    @OneToMany(mappedBy = "custid")
    @JsonBackReference
    private Set<OrderEntity> orderstbls = new LinkedHashSet<>();

}