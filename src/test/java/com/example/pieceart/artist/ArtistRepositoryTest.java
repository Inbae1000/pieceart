package com.example.pieceart.artist;

import com.example.pieceart.entity.Artist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class ArtistRepositoryTest {
    @Autowired
    private ArtistRepository artistRepository;

    @Test
    public void insertArtist(){
        IntStream.rangeClosed(1,10).forEach(i->{
            Artist artist = Artist.builder()
                    .description("artistDesciption" +i)
                    .name("artistName"+i)
                    .build();
            artistRepository.save(artist);
        });
    }
}
