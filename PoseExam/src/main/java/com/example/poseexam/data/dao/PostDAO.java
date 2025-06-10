package com.example.poseexam.data.dao;

import com.example.poseexam.data.dto.PostDTO;
import com.example.poseexam.data.entity.PostEntity;
import com.example.poseexam.data.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostDAO {
    private final PostRepository postRepository;

    public List<PostEntity> readlist(){
     List<PostEntity> postList= this.postRepository.findAll();
     return postList;
    }
}
