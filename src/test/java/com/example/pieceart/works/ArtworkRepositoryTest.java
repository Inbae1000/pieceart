package com.example.pieceart.works;

import com.example.pieceart.entity.Works;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.IntStream;

@SpringBootTest
class ArtworkRepositoryTest {
    @Autowired
    private WorksRepository artworkRepository;

    @Test
    public void insertArtwork(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Works art = Works.builder().category("painting")
                    .createdYear("2022-03-"+i)
                    .description("description"+i)
                    .name("name"+i)
                    .build();
            artworkRepository.save(art);
        });
    }


}