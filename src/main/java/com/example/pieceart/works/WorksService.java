package com.example.pieceart.works;

import com.example.pieceart.artist.ArtistDTO;
import com.example.pieceart.entity.Artist;
import com.example.pieceart.entity.Works;


import java.util.List;

public interface WorksService {


    List<WorksDTO> findByPage(int page);
    List<WorksDTO> findAll();
    List<WorksDTO> findByArtistName(String name);
    List<WorksDTO> findByWorksName(String name);
    WorksDTO findById(Long id);
    WorksDTO create(WorksDTO worksDTO);
    void delete(Long id);
    WorksDTO update(Long id, WorksDTO worksDTO);

    default Works dtoToEntity(WorksDTO dto){

        // json으로 파싱할때 타입이 안맞아서 안넘어옴
        // test에서 실행할때는 저장 됐음
    Artist artist = Artist.builder().id(dto.getArtistId()).build();
        Works works = Works.builder()
                .name((dto.getName()))
                .category(dto.getCategory())
                .description(dto.getDescription())
                .createdYear(dto.getCreatedYear())
                .auctionStartDate(dto.getAuctionStartDate())
                .auctionEndDate(dto.getAuctionEndDate())
                .initialPrice(dto.getInitialPrice())
                .artist(artist)
                .images(dto.getImages())
                .build();
        return works;

    }

    default WorksDTO entityToDTO(Works works){

        WorksDTO worksDTO = WorksDTO.builder()
                .id(works.getId())
                .name(works.getName())
                .category(works.getCategory())
                .description(works.getDescription())
                .createdYear(works.getCreatedYear())
                .auctionStartDate(works.getAuctionStartDate())
                .auctionEndDate(works.getAuctionEndDate())
                .initialPrice(works.getInitialPrice())
                .artist(works.getArtist())
                .images(works.getImages())
                .build();
        return worksDTO;
    }


}

