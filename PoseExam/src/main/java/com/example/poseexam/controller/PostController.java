package com.example.poseexam.controller;

import com.example.poseexam.data.dto.PostDTO;
import com.example.poseexam.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping(value = "/readlist")
    public ResponseEntity<List<PostDTO>> readlist(){
       List<PostDTO> readlist =this.postService.readlist();
       return ResponseEntity.ok().body(readlist);
    }
}
