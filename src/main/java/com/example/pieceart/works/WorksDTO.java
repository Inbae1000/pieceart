package com.example.pieceart.works;

import com.example.pieceart.entity.Artist;
import com.example.pieceart.entity.Image;
import lombok.*;


import java.time.LocalDate;
import java.util.Set;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorksDTO {
    private Long id;
    private String name;
    private String category;
    private String description;
    private String createdYear;
    private LocalDate auctionStartDate;
    private LocalDate auctionEndDate;
    private Integer initialPrice;
    private Long artistId;
    private Artist artist;
    private Set<Image> images;
    private String size;
}
