package com.example.image_upload.controller;

import com.example.image_upload.data.entity.ImageEntity;
import com.example.image_upload.data.repository.ImageEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FileUploadController {
    @Value("${file.upload-dir}") // 상대 경로로서 현재 프로젝트 폴더가 루트
    private String uploadDir;

    private final ImageEntityRepository imageRepository;

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("image") MultipartFile file) {
        try {
            // 저장 경로 준비
            String originalFilename = file.getOriginalFilename();
            String fileName = UUID.randomUUID() + "_" + originalFilename;
            //UUID.randomUUID() : 128비트(16바이트) 길이의 고유한 식별자를 생성(랜덤기반)
            //클라이언트가 보내는 파일이름이 중복될수 있으므로 이를 방지
            Path filePath = Paths.get(uploadDir + fileName);

            // 디렉토리 없으면 생성
            Files.createDirectories(filePath.getParent());
            // 파일 저장
            Files.write(filePath, file.getBytes());

            // DB에 경로 저장
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setPath(filePath.toString()); // or 상대 경로만 저장도 가능
            imageRepository.save(imageEntity);

            return ResponseEntity.ok().body(Map.of("path", originalFilename));
        } catch (IOException e) {
            return ResponseEntity.status(500).body("fail");
        }


    }
}
