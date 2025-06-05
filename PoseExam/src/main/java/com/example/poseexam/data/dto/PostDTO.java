package com.example.poseexam.data.dto;

import com.example.poseexam.data.entity.AuthenEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
        private Integer id;

        private String username;// 확인//

        private String title;

        private String writer;

        private String body;

        private LocalDate pdate;

}
