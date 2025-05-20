package com.example.jpa_exam.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "buytbl")
public class BuyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num", nullable = false)
    private Integer id;

    @ManyToOne//*(fetch = FetchType.LAZY, optional = false)* 페치타임 레이지, 지연으로 가져올 수 있다. 바이엔티티점 겟 ㅎ
    @JoinColumn(name = "userid", nullable = false)
    private UserEntity user;// 같은 유저가 여러번 나올수 있다는 뜻이다 (내가 먼저임: 많음) 유저로 바꿔도 인식한다고해서 바꿈.

    @Column(name = "prodname", nullable = false, length = 6)
    private String prodname;

    @Column(name = "groupname", length = 4)
    private String groupname;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "amount", nullable = false)
    private Short amount;

}