package com.example.pieceart.image;

import com.example.pieceart.entity.Works;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private Long id;
    private String type;
    private String imageUrl;
    private String serverImageUrl;
    private Works works;

}