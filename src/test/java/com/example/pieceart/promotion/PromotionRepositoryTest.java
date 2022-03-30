package com.example.pieceart.promotion;

import com.example.pieceart.entity.Promotion;
import com.example.pieceart.entity.Works;
import com.example.pieceart.works.WorksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class PromotionRepositoryTest {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private WorksRepository worksRepository;



    @Test
    public void insertPromotion(){
        IntStream.rangeClosed(87,100).forEach(i->{
            long a = i;
            // id() 에 long으로 지정한 변수만 들어갈수 있음
            Works works = Works.builder().id(a).build();

            Promotion promotion = Promotion.builder()
                    .eventTitle("event" +i)
                    .eventDescription("event" + i)
                    .works(works)
                    .build();
            promotionRepository.save(promotion);

        });
    }

    @Transactional
    @Test
    public void test1(){
        Optional<Promotion> result = promotionRepository.findById(10L);

        Promotion promotion = result.get();

        System.out.println(promotion);
        System.out.println(promotion.getId());
        System.out.println(promotion.getEventTitle());
        System.out.println(promotion.getEventDescription());
        System.out.println(promotion.getWorks());
    }
}
