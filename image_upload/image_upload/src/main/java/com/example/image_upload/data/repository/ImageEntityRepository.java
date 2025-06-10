package com.example.image_upload.data.repository;

import com.example.image_upload.data.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageEntityRepository extends JpaRepository<ImageEntity, Long> {
}
