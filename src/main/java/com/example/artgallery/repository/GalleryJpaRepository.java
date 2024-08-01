package com.example.artgallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.artgallery.model.Gallery;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryJpaRepository extends JpaRepository<Gallery, Integer> {

}
