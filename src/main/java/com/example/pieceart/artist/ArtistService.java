package com.example.pieceart.artist;


import com.example.pieceart.entity.Artist;

public interface ArtistService {
    ArtistDTO findById(Long id);
    ArtistDTO create(ArtistDTO artistDTO);
    void delete(Long id);
    ArtistDTO update(Long id, ArtistDTO artistDTO);


    default Artist dtoToEntity(ArtistDTO dto){

        Artist artist = Artist.builder()
                .achieve(dto.getAchieve())
                .degree(dto.getDegree())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
        return artist;
    }

    default ArtistDTO entityToDTO(Artist artist){

        ArtistDTO artistDTO = ArtistDTO.builder()
                .id(artist.getId())
                .achieve(artist.getAchieve())
                .degree(artist.getDegree())
                .name(artist.getName())
                .description(artist.getDescription())
                .build();
        return artistDTO;
    }
}
