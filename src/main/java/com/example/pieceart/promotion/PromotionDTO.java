package com.example.pieceart.promotion;

import com.example.pieceart.entity.Works;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDTO {
    private Long id;
    private String eventTitle;
    private String eventDescription;
    private Long worksId;
    private Works works;


}
