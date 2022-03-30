package com.example.pieceart.artist;


import com.example.pieceart.entity.Artist;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ArtistServiceImpl implements ArtistService{
    private final ArtistRepository artistRepository;

    @Override
    @Transactional
    public ArtistDTO findById(Long id){
        Optional<Artist> artist = artistRepository.findById(id);
        if(artist.isPresent()){
            Artist artist1 = artist.get();
            return entityToDTO(artistRepository.save(artist1));
        }
        return null;
    }


    @Override
    @Transactional
    public ArtistDTO create(ArtistDTO artistDTO){
        Artist created  = artistRepository.save(dtoToEntity(artistDTO));
        return entityToDTO(created);
    }

    public void delete(Long id){
        artistRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ArtistDTO update(Long id, ArtistDTO artistDTO){
        return artistRepository.findById(id)
                .map(artist->{
                    artist.setName(artistDTO.getName());
                    artist.setAchieve(artistDTO.getAchieve());
                    artist.setDegree(artistDTO.getDegree());
                    artist.setDescription(artistDTO.getDescription());
                    return entityToDTO(artistRepository.save(artist));
                }).get();
    }
}
