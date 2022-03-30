package com.example.pieceart.artist;


import com.example.pieceart.entity.Artist;
import com.example.pieceart.entity.Works;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDTO {
    private Long id;
    private String achieve;
    private String degree;
    private String name;
    private String description;
    private Works works;

}
