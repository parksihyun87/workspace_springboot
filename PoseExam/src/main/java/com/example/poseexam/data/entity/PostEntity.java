package com.example.poseexam.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posttbl")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postnumber", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private AuthenEntity userObj;

    @Column(name = "title", length = 20)
    private String title;

    @Column(name = "writer", length = 20)
    private String writer;

    @Lob
    @Column(name = "body")
    private String body;

    @Column(name = "pdate")
    private LocalDate pdate;

}