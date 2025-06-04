package com.example.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;//엔티티는 테이블과 매핑// 엔티티는 행하나와 연결되는 객체다.
@Entity
@Table(name="usertbl")// 쿡디비랑 먼저 연결한 후 테이블로 유저티비엘 (매핑 된 거임)
@Data

@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id//자카르타. 기본키다라고 해줌.
    private String userid;
    private String username;
    private int birthyear;
    private String addr;
    private String mobile1;
    private String mobile2;
    private int height;
    private Date mdate;
}
