package com.example.poseexam.service;

import com.example.poseexam.data.dao.PostDAO;
import com.example.poseexam.data.dto.PostDTO;
import com.example.poseexam.data.entity.PostEntity;
import com.example.poseexam.data.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostDAO postDAO;

    public List<PostDTO> readlist(){
        List<PostDTO> dtoReadList= new ArrayList<PostDTO>();
        List<PostEntity> readlist =this.postDAO.readlist();

        for(PostEntity postEntity:readlist){
            PostDTO postDTO =PostDTO.builder()
                    .id(postEntity.getId())
                    .username(postEntity.getUserObj().getUsername())
                    .title(postEntity.getTitle())
                    .writer(postEntity.getWriter())
                    .body(postEntity.getBody())
                    .pdate(postEntity.getPdate())
                    .build();
            dtoReadList.add(postDTO);
        }
        return dtoReadList;
    }



}
