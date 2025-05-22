package com.example.jpa_exam.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    @Size(max = 8, message = "아이디는 8자 이하만 가능합니다.")
    private String userid;
    @Size(max = 10, message = "이름은 10자 이하만 가능합니다.")
    private String username;
    private Integer birthyear;
    @Size(max = 2, message = "지역은 2자 이하만 가능합니다.")
    private String addr;
    private String mobile1;
    private String mobile2;
    private Short height;
    private LocalDate mdate;
}
