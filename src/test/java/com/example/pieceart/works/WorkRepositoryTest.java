package com.example.pieceart.works;

import com.example.pieceart.entity.Artist;
import com.example.pieceart.entity.Notice;
import com.example.pieceart.entity.Works;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class WorkRepositoryTest {
    @Autowired
    private WorksRepository worksRepository;


    @Test
    public void insertWorks(){
        long a = 1;
        Artist artist = Artist.builder().id(a).build();


        Works works = Works.builder()
                .category("category")
                .description("description")
                .name("name")
                .artist(artist)
                .build();
        worksRepository.save(works);

    }

    @Transactional
    @Test
    public void testRead(){
        Optional<Works> result = worksRepository.findById(100L);
        Works works = result.get();

        System.out.println(works);
        System.out.println(works.getName());
    }

    @Transactional
    @Test
    public void testId(){
        Iterable<Works> works = worksRepository.findWorksByArtist("Lee");
        works.forEach(work -> {
                    System.out.println(work.getName());
                    System.out.println(work.getArtist().getName());
                    System.out.println("===================================");
        });



//        Optional<Works> test = worksRepository.findWorksByArtist("Lee");
//
//        Works works = test.get();
//
//        System.out.println(works.getName());
//        System.out.println(works.getArtist().getName());
    }

}
