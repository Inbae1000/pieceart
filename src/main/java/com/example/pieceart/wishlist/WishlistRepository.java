package com.example.pieceart.wishlist;

import com.example.pieceart.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

}
