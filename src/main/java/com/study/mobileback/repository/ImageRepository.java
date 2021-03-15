package com.study.mobileback.repository;

import com.study.mobileback.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
