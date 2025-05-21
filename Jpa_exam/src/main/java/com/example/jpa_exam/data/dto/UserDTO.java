package com.example.jpa_exam.data.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String userid;
    private String username;
    private Integer birthyear;
    private String addr;
    private String mobile1;
    private String mobile2;
    private Short height;
    private LocalDate mdate;
}
