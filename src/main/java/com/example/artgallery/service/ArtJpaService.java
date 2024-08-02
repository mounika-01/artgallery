package com.example.artgallery.service;

import com.example.artgallery.model.Art;
import com.example.artgallery.model.Artist;
import com.example.artgallery.repository.ArtRepository;
import com.example.artgallery.repository.ArtJpaRepository;
import com.example.artgallery.repository.ArtistJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtJpaService implements ArtRepository {
    @Autowired
    private ArtJpaRepository artJpaRepository;

    @Autowired
    private ArtistJpaRepository artistJpaRepository;

    @Override
    public ArrayList<Art> getArts() {
        List<Art> artList = artJpaRepository.findAll();
        return new ArrayList<>(artList);
    }

    @Override
    public Art getArtById(int artId) {
        try {
            return artJpaRepository.findById(artId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Override
    public Art addArt(Art art) {
        try {
            int artistId = art.getArtist().getArtistId();
            Artist artist = artistJpaRepository.findById(artistId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            art.setArtist(artist);
            return artJpaRepository.save(art);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Override
    public Art updateArt(int artId, Art art) {
        try {
            Art newArt = artJpaRepository.findById(artId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            if (art.getArtTitle() != null) {
                newArt.setArtTitle(art.getArtTitle());
            }
            if (art.getTheme() != null) {
                newArt.setTheme(art.getTheme());
            }
            if (art.getArtist() != null) {
                int artistId = art.getArtist().getArtistId();
                Artist artist = artistJpaRepository.findById(artistId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                newArt.setArtist(artist);
            }
            return artJpaRepository.save(newArt);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Override
    public void deleteArt(int artId) {
        try {
            artJpaRepository.deleteById(artId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Override
    public Artist getArtArtist(int artId) {
        try {
            Art art = artJpaRepository.findById(artId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            return art.getArtist();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
