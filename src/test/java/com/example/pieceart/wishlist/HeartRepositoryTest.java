package com.example.pieceart.wishlist;

import com.example.pieceart.entity.Works;
import com.example.pieceart.entity.Wishlist;
import com.example.pieceart.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class HeartRepositoryTest {
    @Autowired
    WishlistRepository heartRepository;

    @Test
    public void insertHeart(){
        IntStream.rangeClosed(1,200).forEach(i->{
            Long artworkId = (long)(Math.random()*100)+1;
            Long userId = (long)(Math.random()*100)+1;
            Member member = Member.builder().id(userId).build();

            Wishlist wishlist = Wishlist.builder().member(member).works(Works.builder().id(artworkId).build()).build();

            heartRepository.save(wishlist);
        });
    }
}