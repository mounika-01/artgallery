package com.example.artgallery.repository;

import com.example.artgallery.model.Artist;

import com.example.artgallery.model.Art;

import java.util.ArrayList;

import java.util.List;

public interface ArtRepository {
    ArrayList<Art getArtS();

    Art getArtById(int artId);

    Art addArt(Art art);

    Art art-com.example.artgallery.repository.ArtRepository.updateArt(int,Art);

    Art updateArt(int artId, Art art);

    void deleteArt(int artId);

    Artist getArtArtist(int artId);
}