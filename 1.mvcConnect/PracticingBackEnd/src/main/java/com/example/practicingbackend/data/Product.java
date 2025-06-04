package com.example.practicingbackend.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//엔티티(테이블 읽기-행), 테이블(name="")테네는말,데이타(겟셋), allargscon,noargcon(상속관련2개)
// etdan
@Entity
@Table(name="producttbl2")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
// @Id ig
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
Integer id;
String title;
Integer price;
String imgSrc;
}
